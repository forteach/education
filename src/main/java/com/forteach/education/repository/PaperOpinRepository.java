package com.forteach.education.repository;

import com.forteach.education.domain.PaperOpin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:08
 * @Version: 1.0
 * @Description: 试卷判断题
 */
public interface PaperOpinRepository extends JpaRepository<PaperOpin, String> {

}
