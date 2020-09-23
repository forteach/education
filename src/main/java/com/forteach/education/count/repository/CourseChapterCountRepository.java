package com.forteach.education.count.repository;

import com.forteach.education.count.domain.CourseChapterCount;
import com.forteach.education.count.dto.ICourseCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:53
 * @version: 1.0
 * @description:
 */
public interface CourseChapterCountRepository extends JpaRepository<CourseChapterCount, String> {

    /**
     * 查询课程统计信息
     *
     * @param courseId
     * @param chapterId
     * @param classId
     * @param circleId
     * @return
     */
    @Query(value = " SELECT " +
            " ccc.courseId AS courseId, " +
            " ccc.chapterId AS chapterId, " +
            " ccc.classId AS classId, " +
            " ccc.classStudentsNumber AS studentsNumber, " +
            " ccc.joinNumber AS joinNumber, " +
            " ccc.drillNumber AS drillNUmber, " +
            " ccc.prepareNumber AS prepareNumber, " +
            " ccc.homeworkNumber AS homeWorkNumber, " +
            " ccc.interactionNumber AS interactionNumber, " +
            " ccc.rewardsNumber AS rewardsNumber, " +
            " ccc.taskNumber AS taskNumber " +
            " FROM CourseChapterCount AS ccc " +
            " WHERE ccc.isValidated = '0' " +
            " AND ccc.courseId = ?1 " +
            " AND ccc.chapterId = ?2 " +
            " AND ccc.classId = ?3 " +
            " AND ccc.circleId = ?4 OR ?4 IS NULL OR ?4 =''")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    List<ICourseCount> findCourseCount(String courseId, String chapterId, String classId, String circleId);
}
