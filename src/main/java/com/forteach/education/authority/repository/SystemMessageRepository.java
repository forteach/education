package com.forteach.education.authority.repository;

import com.forteach.education.databank.domain.SystemMessage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:23
 * @Version: 1.0
 * @Description: 系统公告通知  通知方式：1通知所有人，0不采用
 */
public interface SystemMessageRepository extends JpaRepository<SystemMessage, String> {
}
