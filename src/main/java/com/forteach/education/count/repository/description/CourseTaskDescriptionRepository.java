package com.forteach.education.count.repository.description;

import com.forteach.education.count.domain.description.CourseTaskDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:57
 * @version: 1.0
 * @description:
 */
public interface CourseTaskDescriptionRepository extends JpaRepository<CourseTaskDescription, String> {

    /**
     * 获取任务详情
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param classId
     * @return
     */
    List<CourseTaskDescription> findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);
}
