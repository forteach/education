package com.forteach.education.authority.repository;


import com.forteach.education.authority.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @Description:　系统角色
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:00
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {

    /**
     * 判断角色名称是否存在
     *
     * @param roleName
     * @param isValidated
     * @return
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    SysRole findSysRoleByRoleNameAndIsValidated(String roleName, String isValidated);

    /**
     * 找到可用的权限角色
     *
     * @param isValidated
     * @return
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    List<SysRole> findByIsValidatedEquals(String isValidated);

    /**
     * 根据id找到角色
     *
     * @param id
     * @return
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    SysRole findByRoleId(String id);

    /**
     * 删除权限角色
     *
     * @param id
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    void deleteByRoleId(String id);
}
