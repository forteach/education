package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.SysRole;
import com.forteach.education.authority.domain.SysUsers;
import com.forteach.education.authority.domain.UserRole;
import com.forteach.education.common.keyword.Dic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-8 17:58
 * @version: 1.0
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleRepositoryTest {

    @Resource
    private UserRoleRepository userRoleRepository;
    @Resource
    private SysUsersRepository sysUsersRepository;
    @Resource
    private SysRoleRepository sysRoleRepository;

    @Test
    public void save() {
        Optional<String> sysRoleId = sysRoleRepository.findByIsValidatedEquals(Dic.TAKE_EFFECT_OPEN).stream().filter(sysRole -> "teacher".equals(sysRole.getRoleName()))
                .map(SysRole::getRoleId).findFirst();
        ArrayList<UserRole> userRoles = new ArrayList<>();
        List<SysUsers> sysUsers = sysUsersRepository.findByIsValidatedEquals(Dic.TAKE_EFFECT_OPEN);
        sysRoleId.ifPresent(s -> sysUsers.forEach(sy -> {
            userRoles.add(
                    UserRole.builder()
                            .userId(sy.getId())
                            .roleId(s)
                            .build());
        }));
        userRoleRepository.saveAll(userRoles);
    }
}