package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:43
 * @Version: 1.0
 * @Description:　系统用户
 */
public interface SysUsersRepository extends JpaRepository<SysUsers, String> {


    /**
     * 有效的教师信息
     *
     * @param isValidated
     * @return
     */
    @Transactional(readOnly = true)
    List<SysUsers> findByIsValidatedEquals(String isValidated);
}
