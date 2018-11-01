package com.forteach.education.service;

import com.forteach.education.domain.User;
import com.forteach.education.domain.UserRole;
import com.forteach.education.repository.UserRepository;
import com.forteach.education.repository.UserRoleRepository;
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
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoleRepository.save(userRole);
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
    public User edit(User user) {
        user.setUTime(new Date());
        user.setCTime(new Date());
       return userRepository.save(user);
    }



}
