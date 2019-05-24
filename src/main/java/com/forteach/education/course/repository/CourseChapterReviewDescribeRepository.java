package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseChapterReviewDescribe;
import com.forteach.education.course.dto.ICourseChapterReviewCountDto;
import com.forteach.education.course.dto.IStudentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 11:11
 * @version: 1.0
 * @description:
 */
public interface CourseChapterReviewDescribeRepository extends JpaRepository<CourseChapterReviewDescribe, String> {

    /**
     * 查询符合条的评论详情数量
     * @param isValidated
     * @param chapterId
     * @param studentId
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    Long countByIsValidatedEqualsAndChapterIdAndStudentId(String isValidated, String chapterId, String studentId);


    /**
     * 查询评价平均分和评价人数 AverageScore ReviewAmount
     * @param chapterId
     * @return
     */
    @Query(value = "SELECT AVG(score) AS averageScore, COUNT(studentId) AS reviewAmount FROM CourseChapterReviewDescribe WHERE isValidated = '0' AND chapterId = ?1 ")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    ICourseChapterReviewCountDto averageScoreAndSum(String chapterId);


    /**
     * 查询课程章节评价的学生信息
     * @param chapterId
     * @return
     */
    @Transactional(readOnly = true)
    @Query(value = " SELECT s.id AS studentId, s.userName AS studentName, s.portrait AS portrait, s.classId AS classId FROM StudentEntitys AS s " +
            " WHERE s.id IN (SELECT DISTINCT studentId FROM CourseChapterReviewDescribe WHERE isValidated = '0' AND chapterId = ?1 )")
    List<IStudentDto> findCourseChapterReviewByChapterId(String chapterId);


    /**
     * 查询有效的学生课程章节评分
     * @param isValidated
     * @param studentId
     * @param chapterId
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    CourseChapterReviewDescribe findByIsValidatedEqualsAndStudentIdAndChapterId(String isValidated, String studentId, String chapterId);
}
