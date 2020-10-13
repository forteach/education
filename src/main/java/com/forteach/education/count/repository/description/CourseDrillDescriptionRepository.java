package com.forteach.education.count.repository.description;

import com.forteach.education.count.domain.description.CourseDrillDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:50
 * @version: 1.0
 * @description:
 */
public interface CourseDrillDescriptionRepository extends JpaRepository<CourseDrillDescription, String> {

    /**
     * 获取练习详情
     *
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param classId
     * @return
     */
    List<CourseDrillDescription> findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);
}
