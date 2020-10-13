package com.forteach.education.count.repository.description;

import com.forteach.education.count.domain.description.CourseJoinChapterDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:55
 * @version: 1.0
 * @description:
 */
public interface CourseJoinChapterDescriptionRepository extends JpaRepository<CourseJoinChapterDescription, String> {


    /**
     * 根据课程id 查询加入信息
     *
     * @param isValidated
     * @param circleId
     * @return
     */
    List<CourseJoinChapterDescription> findByIsValidatedEqualsAndCircleId(String isValidated, String circleId);

    @Transactional(readOnly = true)
    List<CourseJoinChapterDescription> findAllByIsValidatedEqualsAndCourseIdAndStudentIdOrderByCreateTimeDesc(String isValidated, String courseId, String studentId);
}
