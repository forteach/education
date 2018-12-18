package com.forteach.education.authority.repository;


import com.forteach.education.authority.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description: 用户角色
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:00
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    /**
     * 删除权限角色
     *
     * @param roleId
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByRoleId(String roleId);

    /**
     * 删除权限角色
     *
     * @param userIds
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByUserIdIn(List<String> userIds);

    UserRole findByUserIdIs(String userId);

}
