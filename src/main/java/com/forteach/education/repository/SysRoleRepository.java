package com.forteach.education.repository;

import com.forteach.education.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:00
 */
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {

    SysRole findSysRoleByRoleNameAndIsValidated(String roleName,String isValidated);

    List<SysRole> findByIsValidatedEquals(String isValidated);

    SysRole findByRoleId(String id);

    @Modifying
    @Transactional
    Long deleteByRoleId(String id);
}
