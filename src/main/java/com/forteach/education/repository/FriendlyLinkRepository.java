package com.forteach.education.repository;

import com.forteach.education.domain.FriendlyLink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:32
 * @Version: 1.0
 * @Description: 友情资讯链接
 */
public interface FriendlyLinkRepository extends JpaRepository<FriendlyLink, String> {
}
