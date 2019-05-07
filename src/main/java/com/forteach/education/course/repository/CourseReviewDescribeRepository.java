package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseReviewDescribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-5 13:46
 * @version: 1.0
 * @description:
 */
public interface CourseReviewDescribeRepository extends JpaRepository<CourseReviewDescribe, String> {

    /**
     * 根据课程查询有效的课程信息
     * @param isValidate
     * @param courseId
     * @return
     */
    @Transactional(readOnly = true)
    List<CourseReviewDescribe> findByIsValidatedEqualsAndCourseIdOrderByCreateTimeDesc(String isValidate, String courseId);
}
