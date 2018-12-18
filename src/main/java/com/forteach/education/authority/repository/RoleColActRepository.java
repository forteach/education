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

    List<RoleColAct> findByRoleIdEquals(String roleId);

    List<RoleColAct> findByColIdAndRoleId(String colId, String roleId);

    @Modifying
    @Transactional(rollbackOn = Exception.class)
    void deleteByRoleIdIs(String roleId);

}
