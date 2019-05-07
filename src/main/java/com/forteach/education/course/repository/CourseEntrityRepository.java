package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 16:29
 * @Version: 1.0
 * @Description:　科目
 */
public interface CourseEntrityRepository extends JpaRepository<CourseEntity, String> {

    @Transactional(readOnly = true)
    List<CourseEntity> findByIsValidated(String isValidated);

    @Transactional(readOnly = true)
    List<CourseEntity> findByIsValidatedEqualsAndCourseIdIn(String isValidated, List<String> courseId);
}
