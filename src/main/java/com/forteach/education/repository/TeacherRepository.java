package com.forteach.education.repository;

import com.forteach.education.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:41
 * @Version: 1.0
 * @Description: 教师
 */
public interface TeacherRepository extends JpaRepository<Teacher, String> {

}
