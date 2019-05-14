package com.forteach.education.count.repository;

import com.forteach.education.count.domain.CoursePrepareCount;
import com.forteach.education.count.dto.ICourseCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional(rollbackFor = Exception.class)
    CoursePrepareCount findByIsValidatedEqualsAndCourseIdAndChapterIdAndClassId(String isValidated, String courseId, String chapterId, String classId);


    /**
     * 查寻统计有效信息通过条件课程，章节，班级
     * @param courseId 课程id
     * @param chapterId 章节id
     * @param classId 班级id
     * @param circleId 课程id
     * @return　List<ICourseCount> 查询课程信统计息数据
     */
    @Query(value = " SELECT " +
            " cjc.courseId AS courseId, " +
            " cjc.chapterId AS chapterId, " +
            " cjc.classId AS classId, " +
            " cpc.classStudentsNumber AS studentsNumber, " + //学生人数
            " cjc.joinNumber AS joinNumber, " + //加入课堂人数
            " cdc.studentsNumber AS drillNumber, " + //练习人数
            " cpc.studentsNumber AS prepareNumber, " + // 预习人数
            " chc.studentsNumber AS homeWorkNumber, " + // 作业人数
            " cic.studentsNumber AS interactionNumber, " + // 作业人数
            " crc.studentsNumber AS rewardsNumber, " + // 课堂奖励统计人数
            " ctc.studentsNumber AS taskNumber " + // 作业人数
            " FROM CourseJoinChapter AS cjc " +
            " LEFT JOIN CoursePrepareCount AS cpc ON cjc.courseId = cpc.courseId AND " +
            " cjc.chapterId = cpc.chapterId AND cpc.isValidated = '0' " +
            " LEFT JOIN CourseDrillCount AS cdc ON cjc.courseId = cdc.courseId AND " +
            " cjc.chapterId = cdc.chapterId AND cpc.isValidated = '0' " +
            " LEFT JOIN CourseRewardsCount crc ON cjc.courseId = crc.courseId AND" +
            " cjc.chapterId = crc.chapterId AND cpc.isValidated = '0' " +
            " LEFT JOIN CourseInteractionCount cic ON cjc.courseId = cic.courseId AND " +
            " cjc.chapterId = cic.chapterId AND cpc.isValidated = '0' " +
            " LEFT JOIN CourseHomeWorkCount chc ON cjc.courseId = chc.courseId AND " +
            " cjc.chapterId = chc.chapterId AND cpc.isValidated = '0' " +
            " LEFT JOIN CourseTaskCount ctc ON cjc.courseId = ctc.courseId AND " +
            " ctc.chapterId = cjc.chapterId AND cpc.isValidated = '0' " +
            " WHERE cjc.isValidated = '0' AND " +
            " cjc.courseId = ?1 AND cjc.chapterId = ?2 AND cjc.classId = ?3 " +
            " AND cjc.circleId = ?4 OR ?4 IS NULL OR ?4 = ''")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    List<ICourseCount> findCourseCount(String courseId, String chapterId, String classId, String circleId);

}
