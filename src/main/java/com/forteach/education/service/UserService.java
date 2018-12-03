package com.forteach.education.service;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.SysUsers;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-2 17:44
 * @Version: 1.0
 * @Description:
 */
public interface UserService {
//    SysUsers findUserById(String userId);

    WebResult login(SysUsers sysUsers);


    WebResult registerUser(SysUsers user);
}
