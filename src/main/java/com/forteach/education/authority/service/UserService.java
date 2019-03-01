package com.forteach.education.authority.service;

import com.forteach.education.authority.web.req.RegisterUserReq;
import com.forteach.education.authority.web.req.UserLoginReq;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.authority.domain.SysUsers;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-2 17:44
 * @Version: 1.0
 * @Description:
 */
public interface UserService {

    /**
     * 教师端用户登录
     * @param userLoginReq
     * @return
     */
    WebResult login(UserLoginReq userLoginReq);

    /**
     * 教师端用户注册
     * @param registerUserReq
     * @return
     */
    WebResult registerUser(RegisterUserReq registerUserReq);
}
