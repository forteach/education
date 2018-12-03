package com.forteach.education.service.impl;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.SysUsers;
import com.forteach.education.domain.UserRole;
import com.forteach.education.repository.UserRepository;
import com.forteach.education.repository.UserRoleRepository;
import com.forteach.education.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-2 17:44
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleRepository userRoleRepository;


    @Override
    public WebResult login(SysUsers sysUsers) {
        SysUsers user = userRepository.findByUserName(sysUsers.getUserName());
        if(user == null){
            return WebResult.failException("用户不存在");
        }else if (!user.getPassWord().equals(sysUsers.getPassWord())){
            return WebResult.failException("密码错误");
        }
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("user", user);
        return WebResult.okResult(map);
    }

    @Override
    public WebResult registerUser(SysUsers user) {
        SysUsers sysUsers = userRepository.save(user);
        if (sysUsers == null){
            return WebResult.failException("注册失败，请换个手机号");
        }
        //分配角色
        userRoleRepository.save(UserRole.builder().userId(sysUsers.getId()).roleId("").build());
        return WebResult.okResult(sysUsers);
    }
}
