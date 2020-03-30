package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.SysRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-8 16:27
 * @version: 1.0
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleRepositoryTest {

    @Resource
    private SysRoleRepository sysRoleRepository;
    @Test
    public void findSysRoleByRoleNameAndIsValidated(){
        sysRoleRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN)
                .forEach(System.out::println);
    }

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void save(){
        ArrayList<SysRole> sysRoleArrayList = new ArrayList<>();
        SysRole sysRoleAdmin = new SysRole();
        sysRoleAdmin.setRoleName("admin");
        sysRoleAdmin.setRemark("管理员");
        sysRoleAdmin.setIsValidated(TAKE_EFFECT_OPEN);
//        sysRoleRepository.save(sysRole);
        sysRoleArrayList.add(sysRoleAdmin);

        SysRole sysTeacher = new SysRole();
        sysTeacher.setRoleName("teacher");
        sysTeacher.setRemark("教师");
        sysTeacher.setIsValidated(TAKE_EFFECT_OPEN);
//        sysRoleRepository.save(sysRole);
        sysRoleArrayList.add(sysTeacher);

        SysRole sysRoleStudent = new SysRole();
        sysRoleStudent.setRoleName("student");
        sysRoleStudent.setRemark("学生");
        sysRoleStudent.setIsValidated(TAKE_EFFECT_OPEN);
//        sysRoleRepository.save(sysRole);
        sysRoleArrayList.add(sysRoleStudent);

        sysRoleRepository.saveAll(sysRoleArrayList);
    }
}