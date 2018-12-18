package com.forteach.education.authority.service.impl;

import com.forteach.education.authority.domain.SysUsers;
import com.forteach.education.authority.domain.UserRole;
import com.forteach.education.authority.repository.UserRepository;
import com.forteach.education.authority.repository.UserRoleRepository;
import com.forteach.education.service.UserMgrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:40
 */
@Slf4j
@Service
public class UserMgrServiceImpl implements UserMgrService {

    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private UserRepository userRepository;

    @Override
    @Transactional( rollbackFor = Exception.class)
    public void updateUserRole(String roleId, List<String> userIds) {
        userRoleRepository.deleteByUserIdIn(userIds);
        //存入新的用户角色信息
        userIds.forEach(userId -> {
            userRoleRepository.save(UserRole.builder()
                    .roleId(roleId)
                    .userId(userId)
                    .build());
        });
    }

    /**
     * 修改保存用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional( rollbackFor = Exception.class)
    public SysUsers edit(SysUsers user) {
        user.setUTime(new Date());
        user.setCTime(new Date());
       return userRepository.save(user);
    }



}
