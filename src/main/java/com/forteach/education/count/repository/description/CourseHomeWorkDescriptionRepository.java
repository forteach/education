package com.forteach.education.count.repository.description;

import com.forteach.education.count.domain.description.CourseHomeWorkDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:52
 * @version: 1.0
 * @description:
 */
public interface CourseHomeWorkDescriptionRepository extends JpaRepository<CourseHomeWorkDescription, String> {

    /**
     * 获取课后作业详情
     *
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param classId
     * @return
     */
    List<CourseHomeWorkDescription> findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);

}
