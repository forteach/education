package com.forteach.education.count.repository;

import com.forteach.education.count.domain.CourseTaskCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 14:54
 * @version: 1.0
 * @description:　任务
 */
public interface CourseTaskCountRepository extends JpaRepository<CourseTaskCount, String> {

    /**
     * 查询有效状态的任务情况
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param classId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    CourseTaskCount findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);

}
