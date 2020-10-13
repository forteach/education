package com.forteach.education.authority.service;

import com.forteach.education.authority.web.req.RegisterUserReq;
import com.forteach.education.authority.web.req.UpdatePassWordReq;
import com.forteach.education.authority.web.req.UserLoginReq;
import com.forteach.education.common.keyword.WebResult;

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
     *
     * @param userLoginReq
     * @return
     */
    WebResult login(UserLoginReq userLoginReq);

    /**
     * 教师端用户注册
     *
     * @param registerUserReq
     * @return
     */
    WebResult registerUser(RegisterUserReq registerUserReq);

    /**
     * 重置用户密码
     *
     * @param teacherCode
     * @return
     */
    WebResult resetPassWord(String teacherCode);

    /**
     * 添加教师用户信息
     *
     * @param teacherCode
     * @return
     */
    WebResult addSysTeacher(String teacherCode);

    /**
     * 修改密码
     *
     * @param updatePassWordReq
     * @return
     */
    WebResult updatePassWord(UpdatePassWordReq updatePassWordReq);

    /**
     * 修改教师状态使失效
     *
     * @param teacherCodeStr
     */
    void updateState(String teacherCodeStr);
}
