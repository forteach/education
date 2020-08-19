package com.forteach.education.databank.repository;

import com.forteach.education.databank.domain.ShareUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:25
 * @Version: 1.0
 * @Description: 分享用户
 */
public interface ShareUserRepository extends JpaRepository<ShareUser, String> {
}
