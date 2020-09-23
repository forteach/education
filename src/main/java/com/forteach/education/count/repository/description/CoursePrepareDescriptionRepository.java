package com.forteach.education.count.repository.description;

import com.forteach.education.count.domain.description.CoursePrepareDescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:55
 * @version: 1.0
 * @description:
 */
public interface CoursePrepareDescriptionRepository extends JpaRepository<CoursePrepareDescription, String> {

    /**
     * 获取练习详情
     *
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param classId
     * @return
     */
    List<CoursePrepareDescription> findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);
}
