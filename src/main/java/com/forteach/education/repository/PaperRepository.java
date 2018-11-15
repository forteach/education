package com.forteach.education.repository;

import com.forteach.education.domain.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:27
 * @Version: 1.0
 * @Description:　试卷
 */
public interface PaperRepository extends JpaRepository<Paper, String> {
}
