package com.forteach.education.authority.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-6 13:43
 * @version: 1.0
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceImplTest {

    @Resource
    private RoleServiceImpl roleService;
    @Test
    public void findUsersInfo() {
        roleService.findUsersInfo(0, 20)
                .forEach(System.out::println);
    }
}