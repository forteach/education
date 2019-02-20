package com.forteach.education.service;

import com.forteach.education.authority.domain.SysRole;
import com.forteach.education.authority.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * public class Role
 *
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:41
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {

    @Resource
    private RoleService roleService;

    @Test
    public void edit() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleId("4028098166c8b1fa0166c8b208c10000");
        sysRole.setIsValidated(TAKE_EFFECT_OPEN);
        sysRole.setRemark("测试工程师");
        sysRole.setRoleName("测试工程师");
        log.info("---------------------------------------------");
        log.debug("{}", roleService.edit(sysRole));
        log.info("---------------------------------------------");
    }

    @Test
    public void existsName() {

        log.info("---------------------------------------------");
        log.debug("{}", roleService.existsName("系统管理员"));
        log.info("---------------------------------------------");

    }

    @Test
    public void findRoleInfo() {
        log.info("---------------------------------------------");
        log.debug("{}", roleService.findRoleInfo());
        log.info("---------------------------------------------");
    }

    @Test
    public void findUsersInfo() {
        log.info("---------------------------------------------");
        log.warn("{}", roleService.findUsersInfo(0, 10, "cTime").toString());
        log.info("---------------------------------------------");
    }

    @Test
    public void deleteRole() {
        log.info("---------------------------------------------");
        roleService.deleteRole("200");
        log.info("---------------------------------------------");
    }

    @Test
    public void sysRoleRepository() {
        log.info("---------------------------------------------");
        log.warn("{}", roleService.findById("4028098166c8b1fa0166c8b208c10000"));
        log.info("---------------------------------------------");
    }

}
