package com.forteach.education.service;

import com.alibaba.fastjson.JSON;
import com.forteach.education.authority.service.AuthorityMgrService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 16:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityMgrServiceTest {

    @Resource
    private AuthorityMgrService authorityMgrService;

    @Test
    public void findTreeTop() {
        log.info("---------------------------------------------");
        log.warn("{}", authorityMgrService.findTreeTop().toString());
        log.info("---------------------------------------------");
    }

    @Test
    public void treeMenu() {
        log.info("---------------------------------------------");
        log.warn("{}", JSON.toJSONString(authorityMgrService.treeMenu()));
        log.info("---------------------------------------------");
    }

    @Test
    public void findColumnOperationByLeafNode() {
        log.info("---------------------------------------------");
        log.warn("{}", JSON.toJSONString(authorityMgrService.findColumnOperationByLeafNode("30c93851763c498a8b6393caba49402e")));
        log.info("---------------------------------------------");
    }

    @Test
    public void findColumnByRoleId() {
        log.info("---------------------------------------------");
        log.warn("{}", JSON.toJSONString(authorityMgrService.findColumnByRoleId("0")));
        log.info("---------------------------------------------");
    }

    @Test
    public void findColumnIdsByRoleId() {
        log.info("---------------------------------------------");
        log.warn("{}", JSON.toJSONString(authorityMgrService.findColumnIdsByRoleId("0")));
        log.info("---------------------------------------------");
    }


}
