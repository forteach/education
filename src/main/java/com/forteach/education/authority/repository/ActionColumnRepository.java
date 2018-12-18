package com.forteach.education.authority.repository;


import com.forteach.education.authority.domain.ActionColumn;
import com.forteach.education.web.vo.ColumnOperationVo;
import com.forteach.education.web.vo.OperationInfoVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description:　系统栏目表
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:02
 */
public interface ActionColumnRepository extends JpaRepository<ActionColumn, Long> {


    List<ActionColumn> findByColIdIsNotNullAndColParentIdIsNull();

    List<ActionColumn> findByIsValidatedEquals(String isValidated);

    @Query(value = "SELECT " +
            "sys_act_id as actId," +
            "sys_act_name as actName," +
            "is_validated as isValidated " +
            "FROM sys_action " +
            "WHERE " +
            "sys_act_id IN ( SELECT sys_act_id FROM role_col_act WHERE col_id = ?1 )", nativeQuery = true)
    List<ColumnOperationVo> findColumnOperation(String colId);

    List<ActionColumn> findByColIdIn(List<String> list);

    @Query(value = "SELECT " +
            "col_parent_id as colParentId," +
            "col_parent_name as colParentName," +
            "col_id as colId," +
            "col_name as colName " +
            "FROM " +
            "action_column " +
            "WHERE " +
            "col_id IN ( SELECT col_id FROM role_col_act WHERE role_id = ?1 ) " +
            "AND " +
            "col_parent_id IS NOT NULL", nativeQuery = true)
    List<OperationInfoVo> findColumnOperationInfo(String roleId);


}

