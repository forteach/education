package com.forteach.education.paper.repository;


import com.forteach.education.paper.domain.PaperScorer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:27
 * @Version: 1.0
 * @Description: 试卷评分
 */
public interface PaperScorerRepository extends JpaRepository<PaperScorer, String> {
}
