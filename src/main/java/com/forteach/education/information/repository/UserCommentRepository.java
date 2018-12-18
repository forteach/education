package com.forteach.education.information.repository;

import com.forteach.education.databank.domain.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:18
 * @Version: 1.0
 * @Description: 用户评论
 */
public interface UserCommentRepository extends JpaRepository<UserComment, String> {
}
