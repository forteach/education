package com.forteach.education.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:53
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMgrServiceTest {

    @Resource
    private com.forteach.education.service.UserMgrService userMgrService;

    @Test
    public void updateUserRole() {
        List<String> userIds = new ArrayList<>();
        userIds.add("123");
        userMgrService.updateUserRole("10", userIds);

    }

}
