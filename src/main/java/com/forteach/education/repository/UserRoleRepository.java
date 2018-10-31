package com.forteach.education.repository;

import com.forteach.education.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:00
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    /**
     * 删除权限角色
     * @param roleId
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByRoleId(String roleId);

    UserRole findByUserIdIs(String userId);

}
