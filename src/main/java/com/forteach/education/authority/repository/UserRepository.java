package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.SysUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    Page<SysUsers> findByIsValidatedEquals(String isValidated, Pageable pageable);

    /**
     * 根据用户查询用户信息
     * @param userName
     * @return
     */
    @Transactional(readOnly = true)
    SysUsers findByUserName(String userName);

    /**
     * 根据教师id 查询教师信息
     * @param teacherId
     * @return
     */
    @Transactional(readOnly = true)
    SysUsers findByTeacherId(String teacherId);
}
