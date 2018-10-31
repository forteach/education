package com.forteach.education.repository;

import com.forteach.education.domain.ActionColumn;
import com.forteach.education.web.vo.ColumnOperationVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description:
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
            "sys_act_id IN ( SELECT sys_act_id FROM role_col_act WHERE col_id = ?1 )",nativeQuery=true)
    List<ColumnOperationVo> findColumnOperation(String colId);


}
