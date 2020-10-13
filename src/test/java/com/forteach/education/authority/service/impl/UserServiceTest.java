package com.forteach.education.authority.service.impl;

import com.forteach.education.authority.domain.SysUsers;
import com.forteach.education.authority.repository.SysUsersRepository;
import com.forteach.education.util.Md5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-29 09:19
 * @version: 1.0
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Resource
    private SysUsersRepository sysUsersRepository;

    @Value("${token.salt}")
    private String salt;

    @Test
    public void saveUser() {
        SysUsers sysUsers = new SysUsers();
        sysUsers.setPassWord(Md5Util.macMD5("root".concat(salt)));
        sysUsers.setTeacherId("admin");
        sysUsers.setUserName("管理员");
        sysUsers.setRegisterPhone("13100000000");
        sysUsersRepository.save(
                sysUsers
        );
    }
}
