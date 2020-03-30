package com.forteach.education.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.forteach.education.authority.domain.SysUsers;
import com.forteach.education.authority.domain.UserRole;
import com.forteach.education.authority.repository.UserRepository;
import com.forteach.education.authority.repository.UserRoleRepository;
import com.forteach.education.authority.web.req.SysUserEditReq;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.service.UserMgrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public SysUsers edit(SysUserEditReq user) {
        Optional<SysUsers> usersOptional = userRepository.findById(user.getId());
        if (usersOptional.isPresent()) {
            SysUsers users = usersOptional.get();
            BeanUtil.copyProperties(user, users);
            return userRepository.save(users);
        }
        MyAssert.isNull(null, DefineCode.ERR0010, "要修改的用户不存在");
        return null;
    }


}
