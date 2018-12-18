package com.forteach.education.databank.repository;

import com.forteach.education.databank.domain.ClassroomQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 16:31
 * @Version: 1.0
 * @Description: 课堂问题
 */
public interface ClassroomQuestionRepository extends JpaRepository<ClassroomQuestion, String> {

}
