package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseReviewDescribe;
import com.forteach.education.course.dto.ICourseReviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-5 13:46
 * @version: 1.0
 * @description:
 */
public interface CourseReviewDescribeRepository extends JpaRepository<CourseReviewDescribe, String> {

    /**
     * 查询最近一条课程评论详情
     *
     * @param courseId
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Query(value = "SELECT " +
            " c.courseId AS courseId, " +
            " c.averageScore AS averageScore, " +
            " c.reviewAmount AS reviewAmount, " +
            " crd.studentId AS studentId, " +
            " s.userName AS studentName, " +
            " s.portrait AS portrait, " +
            " crd.reviewId AS reviewId, " +
            " crd.reviewDescribe AS reviewDescribe, " +
            " cs.classId AS classId, " +
            " cs.className AS className, " +
            " crd.score AS score, " +
            " crd.createTime AS createTime, " +
            " crd.replyTime AS replyTime, " +
            " crd.teacherId AS teacherId, " +
            " t.teacherName AS teacherName " +
            " FROM CourseReviewDescribe AS crd  " +
            " LEFT JOIN Course  AS c ON c.courseId = crd.courseId " +
            " LEFT JOIN Teacher AS t ON crd.teacherId = t.teacherId " +
            " LEFT JOIN StudentEntitys AS s ON crd.studentId = s.id " +
            " LEFT JOIN Classes AS cs ON s.classId = cs.classId " +
            " WHERE crd.isValidated = '0' AND c.isValidated = '0'  " +
            " AND crd.courseId = ?1 " +
            " ORDER BY crd.createTime ")
    List<ICourseReviewDto> findFirstByIsValidatedEqualsAndCourseIdOrderByCreateTimeDesc(String courseId);

    /**
     * 查询计算评价平均分
     *
     * @param courseId
     * @return
     */
    @Transactional(readOnly = true)
    @Query(value = "SELECT AVG(score) AS averageScore FROM CourseReviewDescribe WHERE isValidated = '0' AND courseId = ?1")
    Double avgByCourseId(String courseId);

    /**
     * 查询评论列表
     *
     * @param courseId
     * @return
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Query(value = "SELECT " +
            " crd.studentId AS studentId, " +
            " s.userName AS studentName, " +
            " s.portrait AS portrait, " +
            " crd.reviewId AS reviewId, " +
            " crd.reviewDescribe AS reviewDescribe, " +
            " cs.classId AS classId, " +
            " cs.className AS className, " +
            " crd.score AS score, " +
            " crd.createTime AS createTime, " +
            " crd.replyTime AS replyTime, " +
            " crd.teacherId AS teacherId, " +
            " t.teacherName AS teacherName " +
            " FROM CourseReviewDescribe AS crd  " +
            " LEFT JOIN Course  AS c ON c.courseId = crd.courseId " +
            " LEFT JOIN Teacher AS t ON crd.teacherId = t.teacherId " +
            " LEFT JOIN StudentEntitys AS s ON crd.studentId = s.id " +
            " LEFT JOIN Classes AS cs ON s.classId = cs.classId " +
            " WHERE crd.isValidated = '0' AND c.isValidated = '0'  " +
            " AND crd.courseId = ?1 " +
            " ORDER BY crd.createTime ")
    Page<ICourseReviewDto> findCourseReviewListByCourseIdOrderByCreateTime(String courseId, Pageable pageable);

}
