package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.SysAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:　系统动作
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:01
 */
public interface SysActionRepository extends JpaRepository<SysAction, Long> {
    /**
     * 查询有效的动作信息
     * @param isValidated
     * @return
     */
    @Transactional(readOnly = true)
    List<SysAction> findByIsValidatedEquals(String isValidated);
}
