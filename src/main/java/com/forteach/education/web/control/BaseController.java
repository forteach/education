package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.domain.SysUsers;
import com.forteach.education.exception.TokenException;
import com.forteach.education.util.TokenUtil;
import com.forteach.education.web.vo.CheckTokenVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description:
 * @version: V1.0
 * @author: liu zhenming
 * @Email: 1119264845@qq.com
 * @Date: 2018-07-30 10:49
 */
public class BaseController {

    @Autowired
    protected TokenUtil tokenUtil;


    /**
     * 从redis中获取用户信息
     *
     * @param request request
     * @return 用户信息
     */
    protected SysUsers getUserCache(HttpServletRequest request) {
        String token = request.getHeader("token");
        return getUserCache(token);
    }

    /**
     * 从redis中获取用户信息
     *
     * @param token
     * @return 用户信息
     */
    protected SysUsers getUserCache(String token) {
        if (Objects.equals(null, token) || Objects.equals("", token)) {
            return null;
        }
        Object u = tokenUtil.getTokenObject(token);
        if (u == null) {
            return null;
        }
        return JSONObject.parseObject(u.toString(), SysUsers.class);
    }

    /**
     * 在session中移除用户信息
     *
     * @param request request
     */
    protected void removeUserCache(HttpServletRequest request) {
        String token = request.getHeader("token");
        tokenUtil.delObjectBykey(token);
    }

    /**
     * 从session中获取用户ID
     *
     * @param request request
     * @return 用户ID
     */
    protected String getUserIdFromCache(HttpServletRequest request) {
        SysUsers user = getUserCache(request);
        if (Objects.equals(null, user)) {
            return null;
        }
        return user.getId();
    }

    /**
     * 从session中获取用户ID
     *
     * @param token
     * @return 用户ID
     */
    protected String getUserIdFromCache(String token) {
        SysUsers user = getUserCache(token);
        if (Objects.equals(null, user)) {
            return null;
        }
        return user.getId();
    }

    /**
     * 从session中获取用户ID
     *
     * @param token
     * @return 用户ID
     */
    protected CheckTokenVo isToken(String token) {
        SysUsers user = getUserCache(token);
        if (Objects.equals(null, user)) {
            throw new TokenException("token 无效");
        }
        CheckTokenVo vo = new CheckTokenVo();
        vo.setToken(token);
        return vo;
    }

}
