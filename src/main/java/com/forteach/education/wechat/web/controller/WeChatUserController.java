package com.forteach.education.wechat.web.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.wechat.config.WeChatMiniAppConfig;
import com.forteach.education.wechat.service.WeChatUserService;
import com.forteach.education.wechat.web.req.BindingUserInfoReq;
import io.swagger.annotations.*;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 19-1-10 11:19
 * @Version: 1.0
 * @Description:
 */
@Api(value = "微信用户操作信息", description = "用户操作相关接口", tags = {"微信用户操作"})
@RestController
@RequestMapping("/user")
public class WeChatUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final WeChatUserService weChatUserService;

    @Autowired
    public WeChatUserController(WeChatUserService weChatUserService) {
        this.weChatUserService = weChatUserService;
    }

    @ApiOperation(value = "微信小程序登录接口", notes = "微信小程序登录接口")
    @GetMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "微信登录凭证(code)", dataType = "string", required = true, paramType = "form"),
            @ApiImplicitParam(name = "portrait", value = "用户头像url", dataType = "string", paramType = "form")
    })
    public WebResult login(@ApiParam(name = "code", value = "微信登录code") String code, @ApiParam(value = "portrait") String portrait) {
        MyAssert.blank(code, DefineCode.ERR0010, "code is null");
        final WxMaService wxService = WeChatMiniAppConfig.getMaService();
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            this.logger.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return weChatUserService.bindingToken(session, portrait);
        } catch (WxErrorException e) {
            this.logger.error(e.getMessage(), e);
            return WebResult.failException(e.getMessage());
        }
    }


    @UserLoginToken
    @ApiOperation(value = "绑定微信用户登录信息")
    @PostMapping("/binding")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "身份证姓名", required = true, paramType = "form"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证号码", required = true, paramType = "form"),
            @ApiImplicitParam(name = "signature", value = "sha1( rawData + session_key )", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "rawData", value = "rawData", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "encryptedData", value = "加密数据", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "iv", value = "数据接口返回", dataType = "string", paramType = "form"),
    })
    public WebResult binding(@RequestBody BindingUserInfoReq bindingUserInfoReq, HttpServletRequest request) {
        MyAssert.blank(bindingUserInfoReq.getIdCardNo(), DefineCode.ERR0010, "身份证号码不为空");
        MyAssert.blank(bindingUserInfoReq.getUserName(), DefineCode.ERR0010, "用户名不为空");
        return weChatUserService.bindingUserInfo(bindingUserInfoReq, request);
    }

    /**
     * todo delete
     *
     * @param studentId
     * @return
     */
    @GetMapping("/restart/{studentId}")
    public WebResult restart(@PathVariable("studentId") String studentId) {
        return weChatUserService.restart(studentId);
    }
}
