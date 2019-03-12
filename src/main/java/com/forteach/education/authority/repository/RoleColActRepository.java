package com.forteach.education.authority.repository;


import com.forteach.education.authority.domain.RoleColAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description:　权限和动作
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:01
 */
public interface RoleColActRepository extends JpaRepository<RoleColAct, Long> {

    /**
     * 根据角色ID 获取栏目编号
     * @param roleId
     * @return
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    List<RoleColAct> findByRoleIdEquals(String roleId);

    /**
     * 获取角色栏目对应的动作
     * @param colId
     * @param roleId
     * @return
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    List<RoleColAct> findByColIdAndRoleId(String colId, String roleId);

    /**
     * 删除该角色的所有子栏目
     * @param roleId
     */
    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByRoleIdIs(String roleId);

}
