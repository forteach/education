package com.forteach.education.count.repository;

import com.forteach.education.count.domain.CoursePrepareCount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 10:52
 * @version: 1.0
 * @description: 预习统计信息表
 */
public interface CoursePrepareCountRepository extends JpaRepository<CoursePrepareCount, String> {
    /**
     * 查询有效状态的预习情况
     * @param isValidated
     * @param courseId
     * @param chapterId
     * @param classId
     * @return
     */
    CoursePrepareCount findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);


//    @Query(value = " SELECT * FROM CourseJoinChapter AS cjc LEFT JOIN CoursePrepareCount AS cpc ON cjc.courseId = cpc.courseId AND " +
//            " cjc.chapterId = cpc.chapterId ")
//    ICourseCount findCourseCount();

}
