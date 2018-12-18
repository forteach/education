package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.ActionColumn;
import com.forteach.education.authority.domain.SysUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:　用户
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 9:57
 */
public interface UserRepository extends JpaRepository<SysUsers, String> {

    /**
     * 找到生效的用户列表
     * @param isValidated
     * @param pageable
     * @return
     */
    Page<SysUsers> findByIsValidatedEquals(String isValidated, Pageable pageable);

    SysUsers findByUserName(String userName);
}
