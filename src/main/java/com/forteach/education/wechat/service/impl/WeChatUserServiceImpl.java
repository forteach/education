package com.forteach.education.wechat.service.impl;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.domain.StudentEntitys;
import com.forteach.education.authority.repository.StudentRepository;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.wechat.config.WeChatMiniAppConfig;
import com.forteach.education.wechat.domain.WeChatUserInfo;
import com.forteach.education.wechat.repository.WeChatUserInfoRepository;
import com.forteach.education.wechat.repository.dto.IWeChatUserInfo;
import com.forteach.education.wechat.service.WeChatUserService;
import com.forteach.education.wechat.web.req.BindingUserInfoReq;
import com.forteach.education.wechat.web.resp.WeChatLoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.forteach.education.common.keyword.Dic.*;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 19-1-8 15:05
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class WeChatUserServiceImpl implements WeChatUserService {

    private final StudentRepository studentRepository;

    private final WeChatUserInfoRepository weChatUserInfoRepository;

    private final StringRedisTemplate stringRedisTemplate;

    private final TokenService tokenService;


    @Autowired
    public WeChatUserServiceImpl(StudentRepository studentRepository,
                                 WeChatUserInfoRepository weChatUserInfoRepository,
                                 StringRedisTemplate stringRedisTemplate,
                                 TokenService tokenService) {
        this.studentRepository = studentRepository;
        this.weChatUserInfoRepository = weChatUserInfoRepository;
        this.stringRedisTemplate = stringRedisTemplate;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WebResult bindingUserInfo(BindingUserInfoReq bindingUserInfoReq, HttpServletRequest request) {
        Optional<StudentEntitys> studentEntitys = studentRepository.findByIsValidatedEqualsAndIdCardNo(TAKE_EFFECT_OPEN, bindingUserInfoReq.getIdCardNo())
                .stream()
                .filter(Objects::nonNull)
                .findFirst();
        if (studentEntitys.isPresent()) {
            Optional<WeChatUserInfo> weChatUserInfoOptional = weChatUserInfoRepository.findByOpenId(tokenService.getOpenId(request))
                    .stream()
                    .filter(Objects::nonNull)
                    .findFirst();
            if (weChatUserInfoOptional.isPresent() && WX_INFO_BINDIND_0.equals(weChatUserInfoOptional.get().getBinding())) {
                return WebResult.failException("该微信账号已经认证");
            }
            WeChatUserInfo weChatUserInfo = weChatUserInfoOptional.orElseGet(WeChatUserInfo::new);
            if (checkStudent(bindingUserInfoReq, studentEntitys.get())) {
                final WxMaService wxService = WeChatMiniAppConfig.getMaService();
                String openId = tokenService.getOpenId(request);
                String sessionKey = tokenService.getSessionKey(openId);
                String key = USER_PREFIX.concat(openId);
                // 用户信息校验
                WxMaUserInfo wxMaUserInfo = null;
                if (checkWxInfo(sessionKey, wxService, bindingUserInfoReq)) {
                    // 解密用户信息
                    wxMaUserInfo = wxService.getUserService().getUserInfo(sessionKey, bindingUserInfoReq.getEncryptedData(), bindingUserInfoReq.getIv());
                }
                // 需要更新用户数据信息
                if (wxMaUserInfo != null) {
                    BeanUtils.copyProperties(wxMaUserInfo, weChatUserInfo);
                }
                weChatUserInfo.setBinding(WX_INFO_BINDIND_0);
                weChatUserInfo.setStudentId(studentEntitys.get().getId());
                weChatUserInfo.setClassId(studentEntitys.get().getClassId());
                weChatUserInfo.setOpenId(openId);
                weChatUserInfoRepository.save(weChatUserInfo);
                //保存redis 设置有效期7天
                Map<String, Object> map = BeanUtil.beanToMap(weChatUserInfo);
                //设置token类型为学生微信登录
                map.put("type", TOKEN_STUDENT);
                stringRedisTemplate.opsForHash().putAll(key, map);
                stringRedisTemplate.expire(key, TOKEN_VALIDITY_TIME, TimeUnit.SECONDS);
                return WebResult.okResult("绑定成功");
            }
        }
        return WebResult.failException("身份信息不符, 请联系管理员");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WebResult bindingToken(WxMaJscode2SessionResult session, String portrait) {
        String openId = session.getOpenid();
        String token = tokenService.createToken(openId, TOKEN_STUDENT);
        String binding = WX_INFO_BINDIND_1;

        Optional<WeChatUserInfo> weChatUserInfoOptional = weChatUserInfoRepository.findByOpenId(openId).stream().findFirst();
        if (weChatUserInfoOptional.isPresent()) {
            binding = weChatUserInfoOptional.get().getBinding();
        }

        Map<String, Object> map = BeanUtil.beanToMap(weChatUserInfoOptional.orElseGet(WeChatUserInfo::new));
        map.put("openId", openId);
        map.put("sessionKey", openId);
        map.put("token", token);
        map.put("binding", binding);
        String key = USER_PREFIX.concat(openId);
        stringRedisTemplate.opsForHash().putAll(key, map);
        //设置有效期7天
        stringRedisTemplate.expire(key, TOKEN_VALIDITY_TIME, TimeUnit.SECONDS);

        weChatUserInfoOptional.ifPresent(weChatUserInfo -> {
            studentRepository.findById(weChatUserInfo.getStudentId()).ifPresent(studentEntitys -> {
                if (StrUtil.isNotBlank(portrait)) {
                    weChatUserInfo.setAvatarUrl(portrait);
                    weChatUserInfoRepository.save(weChatUserInfo);
                }
                studentEntitys.setPortrait(portrait);
                studentRepository.save(studentEntitys);
                String studentKey = STUDENT_ADO.concat(weChatUserInfo.getStudentId());
                stringRedisTemplate.opsForHash().put(studentKey, "portrait", portrait);
            });
        });

        IWeChatUserInfo iWeChatUserInfo = weChatUserInfoRepository.findByIsValidatedEqualsAndOpenId(openId);
        WeChatLoginResp weChatLoginResp = new WeChatLoginResp();
        if (iWeChatUserInfo != null) {
            weChatLoginResp.setClassId(iWeChatUserInfo.getClassId());
            weChatLoginResp.setClassName(iWeChatUserInfo.getClassName());
            weChatLoginResp.setPortrait(iWeChatUserInfo.getPortrait());
            weChatLoginResp.setStudentId(iWeChatUserInfo.getStudentId());
            weChatLoginResp.setStudentName(iWeChatUserInfo.getStudentName());
        }
        weChatLoginResp.setBinding(binding);
        weChatLoginResp.setToken(token);
        return WebResult.okResult(weChatLoginResp);
    }

    @Override
    @SuppressWarnings(value = "all")
    @Transactional(rollbackFor = Exception.class)
    public WebResult restart(String string) {
        List<WeChatUserInfo> list = weChatUserInfoRepository.findByStudentId(string);
        if (list.size() > 0) {
            list.forEach(weChatUserInfo -> {
                weChatUserInfoRepository.delete(weChatUserInfo);
            });
            return WebResult.okResult();
        } else {
            return WebResult.okResult("不存要删除的用户");
        }
    }

    /**
     * 校验身份证和姓名在数据库中是否存在
     *
     * @param bindingUserInfoReq
     * @param studentEntitys
     * @return
     */
    private boolean checkStudent(BindingUserInfoReq bindingUserInfoReq, StudentEntitys studentEntitys) {
        return studentEntitys.getUserName().equals(bindingUserInfoReq.getUserName())
                && studentEntitys.getIdCardNo().equals(bindingUserInfoReq.getIdCardNo());
    }

    /**
     * 校验是否是微信发送的数据
     *
     * @param sessionKey
     * @param wxService
     * @param bindingUserInfoReq
     * @return
     */
    private boolean checkWxInfo(String sessionKey, WxMaService wxService, BindingUserInfoReq bindingUserInfoReq) {
        if (StrUtil.isNotBlank(bindingUserInfoReq.getEncryptedData())
                && StrUtil.isNotBlank(bindingUserInfoReq.getSignature())
                && StrUtil.isNotBlank(bindingUserInfoReq.getIv())
                && StrUtil.isNotBlank(bindingUserInfoReq.getRawData())) {
            return wxService.getUserService().checkUserInfo(sessionKey,
                    bindingUserInfoReq.getRawData(), bindingUserInfoReq.getSignature());
        }
        return false;
    }
}
