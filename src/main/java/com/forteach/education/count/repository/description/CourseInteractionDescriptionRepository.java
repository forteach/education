package com.forteach.education.count.repository.description;

import com.forteach.education.count.domain.description.CourseInteractionDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:53
 * @version: 1.0
 * @description:
 */
public interface CourseInteractionDescriptionRepository extends JpaRepository<CourseInteractionDescription, String> {

    /**
     * 获取互动详情
     *
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param classId
     * @return
     */
    List<CourseInteractionDescription> findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);
}
