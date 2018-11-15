package com.forteach.education.repository;

import com.forteach.education.domain.UserAttention;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:46
 * @Version: 1.0
 * @Description:　用户关注的文章
 */
public interface UserAttentionRepository extends JpaRepository<UserAttention, String> {

}
