package com.forteach.education.count.repository;

import com.forteach.education.count.domain.CourseDrillCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 14:52
 * @version: 1.0
 * @description:　练习
 */
public interface CourseDrillCountRepository extends JpaRepository<CourseDrillCount, String> {

    /**
     * 查询有效状态的练习情况
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param classId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    CourseDrillCount findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);
}
