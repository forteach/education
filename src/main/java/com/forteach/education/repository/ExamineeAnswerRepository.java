package com.forteach.education.repository;

import com.forteach.education.domain.ExamineeAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:54
 * @Version: 1.0
 * @Description:　考生答题卡
 */
public interface ExamineeAnswerRepository extends JpaRepository<ExamineeAnswer, String> {
}
