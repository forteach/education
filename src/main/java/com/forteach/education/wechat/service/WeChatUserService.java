package com.forteach.education.wechat.service;


import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.wechat.web.req.BindingUserInfoReq;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 19-1-10 12:05
 * @Version: 1.0
 * @Description:
 */
public interface WeChatUserService {
    /**
     * 绑定微信登录学号和 openId, 进行身份校验，通过取redis 数据库比较
     *
     * @param bindingUserInfoReq
     * @return WebResult
     */
    WebResult bindingUserInfo(BindingUserInfoReq bindingUserInfoReq, HttpServletRequest request);

    /**
     * 生成token并绑定用户上
     *
     * @param session
     * @return
     */
    WebResult bindingToken(WxMaJscode2SessionResult session, String portrait);

    WebResult restart(String string);
}
