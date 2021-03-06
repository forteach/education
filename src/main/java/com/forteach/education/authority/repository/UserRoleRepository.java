package com.forteach.education.authority.repository;


import com.forteach.education.authority.domain.UserRole;
import com.forteach.education.authority.domain.UserRoleFundPrimarykey;
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
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleFundPrimarykey> {

    /**
     * 删除权限角色
     *
     * @param roleId
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    void deleteByRoleId(String roleId);

    /**
     * 删除权限角色
     *
     * @param userIds
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    void deleteByUserIdIn(List<String> userIds);

    /**
     * 获得用户对应的角色
     *
     * @param userId
     * @return
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    UserRole findByUserIdIs(String userId);

}
