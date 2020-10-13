package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.ActionColumn;
import com.forteach.education.web.vo.ColumnOperationVo;
import com.forteach.education.web.vo.OperationInfoVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:　系统栏目表
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:02
 */
public interface ActionColumnRepository extends JpaRepository<ActionColumn, String> {


    /**
     * 查询栏目编号和父栏目编号不为空的栏目
     *
     * @return
     */
    @Transactional(readOnly = true)
    List<ActionColumn> findByColIdIsNotNullAndColParentIdIsNull();

    /**
     * 查询有效的栏目
     *
     * @param isValidated
     * @return
     */
    @Transactional(readOnly = true)
    List<ActionColumn> findByIsValidatedEquals(String isValidated);

    /**
     * 根据栏目id 查询需要的有的操作动作
     *
     * @param colId
     * @return
     */
    @Transactional(readOnly = true)
    @Query(value = "SELECT " +
            "sys_act_id as actId," +
            "sys_act_name as actName," +
            "is_validated as isValidated " +
            "FROM sys_action " +
            "WHERE " +
            "sys_act_id IN ( SELECT sys_act_id FROM role_col_act WHERE col_id = ?1 )", nativeQuery = true)
    List<ColumnOperationVo> findColumnOperation(String colId);

    /**
     * 根据栏目编号找栏目
     *
     * @param list
     * @return
     */
    @Transactional(readOnly = true)
    List<ActionColumn> findByColIdIn(List<String> list);

    /**
     * 获取该角色的所有子栏目信息
     *
     * @param roleId
     * @return
     */
    @Transactional(readOnly = true)
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

    /**
     * 查询是否存在相同列名栏目信息
     *
     * @param isValidated
     * @param colName
     * @return
     */
    @Transactional(readOnly = true)
    ActionColumn findByIsValidatedEqualsAndColName(String isValidated, String colName);


}

