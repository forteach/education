package com.forteach.education.authority.service;

import com.auth0.jwt.JWTVerifier;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2019/2/20 21:59
 * @Version: 1.0
 * @Description:
 */
public interface TokenService {
    /**
     * 用微信openId生成一个一天有效期的token
     * @param openId
     * @return
     */
    String createToken(String openId);

    /**
     * 获取JWT验证
     * @param openId
     * @return
     */
    JWTVerifier verifier(String openId);

    /**
     * 根据用户请求token 信息获取请求的用户信息
     * @param request
     * @return
     */
    String getUserId(HttpServletRequest request);
}
