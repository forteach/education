package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.ActionColumn;
import com.forteach.education.authority.domain.RoleColAct;
import com.forteach.education.authority.domain.SysAction;
import com.forteach.education.authority.domain.SysRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-8 17:06
 * @version: 1.0
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleColActRepositoryTest {

    @Resource
    private ActionColumnRepository actionColumnRepository;
    @Resource
    private RoleColActRepository roleColActRepository;
    @Resource
    private SysActionRepository sysActionRepository;
    @Resource
    private SysRoleRepository sysRoleRepository;

    @Test
//    @Transactional(rollbackFor = Exception.class)
    public void save() {
        List<SysRole> sysRoles = sysRoleRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN);
        List<ActionColumn> actionColumnList = actionColumnRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN);
        List<SysAction> sysActionList = sysActionRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN);
        ArrayList<RoleColAct> roleColActList = new ArrayList<>();
        //管理员admin
        Optional<String> roleIdAdmin =
                sysRoles.parallelStream().filter(sysRole -> sysRole.getRoleName().equals("admin"))
                        .map(SysRole::getRoleId).findFirst();
        roleIdAdmin.ifPresent(s -> actionColumnList.forEach(actionColumn -> {
            sysActionList.forEach(sysAction -> {
                RoleColAct roleColAct = new RoleColAct();
                roleColAct.setColId(actionColumn.getColId());
                roleColAct.setSysActId(sysAction.getSysActId());
                roleColAct.setRoleId(s);
                roleColActList.add(roleColAct);
            });
        }));

        //教师
        Optional<String> roleIdTeacher =
                sysRoles.stream().filter(sysRole -> sysRole.getRoleName().equals("teacher"))
                        .map(SysRole::getRoleId).findFirst();
        roleIdTeacher.ifPresent(s -> actionColumnList.forEach(actionColumn -> {
            sysActionList.parallelStream().filter(d -> !d.getSysActName().equals("物理删除")).forEach(sysAction -> {
                RoleColAct roleColAct = new RoleColAct();
                roleColAct.setColId(actionColumn.getColId());
                roleColAct.setSysActId(sysAction.getSysActId());
                roleColAct.setRoleId(s);
                roleColActList.add(roleColAct);
            });
        }));

        //学生
        Optional<String> roleIdStudent =
                sysRoles.stream().filter(sysRole -> sysRole.getRoleName().equals("student"))
                        .map(SysRole::getRoleId).findFirst();
        roleIdStudent.ifPresent(s -> actionColumnList.forEach(actionColumn -> {
            sysActionList.parallelStream()
                    .filter(d -> !d.getSysActName().equals("物理删除") && !d.getSysActName().equals("逻辑删除"))
                    .forEach(sysAction -> {
                        RoleColAct roleColAct = new RoleColAct();
                        roleColAct.setColId(actionColumn.getColId());
                        roleColAct.setSysActId(sysAction.getSysActId());
                        roleColAct.setRoleId(s);
                        roleColActList.add(roleColAct);
                    });
        }));
        roleColActRepository.saveAll(roleColActList);
    }
}