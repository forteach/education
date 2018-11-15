package com.forteach.education.repository;

import com.forteach.education.domain.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:43
 * @Version: 1.0
 * @Description:　系统用户
 */
public interface SysUsersRepository extends JpaRepository<SysUsers, String> {

}
