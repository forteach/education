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
     * @param courseId
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Query(value = "select " +
            " c.courseId AS courseId, " +
            " c.averageScore AS averageScore, " +
            " c.reviewAmount AS reviewAmount, " +
            " crd.studentId as studentId, " +
            " s.userName as studentName, " +
            " s.portrait as portrait, " +
            " crd.reviewId as reviewId, " +
            " crd.reviewDescribe as reviewDescribe, " +
            " cs.classId as classId, " +
            " cs.className as className, " +
            " crd.score as score, " +
            " crd.createTime as createTime, " +
            " crd.replyTime as replyTime, " +
            " crd.teacherId as teacherId, " +
            " t.teacherName as teacherName " +
            " from CourseReviewDescribe as crd  " +
            " left JOIN Course  AS c ON c.courseId = crd.courseId " +
            " left join Teacher AS t on crd.teacherId = t.teacherId " +
            " left join StudentEntitys as s on crd.studentId = s.id " +
            " left join Classes as cs on s.classId = cs.classId " +
            " where crd.isValidated = '0' and c.isValidated = '0'  " +
            " and crd.courseId = ?1 " +
            " ORDER BY crd.createTime ")
    List<ICourseReviewDto> findFirstByIsValidatedEqualsAndCourseIdOrderByCreateTimeDesc(String courseId);

    /**
     * 查询计算评价平均分
     * @param courseId
     * @return
     */
    @Transactional(readOnly = true)
    @Query(value = "SELECT AVG(score) AS averageScore FROM CourseReviewDescribe WHERE isValidated = '0' AND courseId = ?1")
    Double avgByCourseId(String courseId);

    /**
     * 查询评论列表
     * @param courseId
     * @return
     */
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    @Query(value = "select " +
            " crd.studentId as studentId, " +
            " s.userName as studentName, " +
            " s.portrait as portrait, " +
            " crd.reviewId as reviewId, " +
            " crd.reviewDescribe as reviewDescribe, " +
            " cs.classId as classId, " +
            " cs.className as className, " +
            " crd.score as score, " +
            " crd.createTime as createTime, " +
            " crd.replyTime as replyTime, " +
            " crd.teacherId as teacherId, " +
            " t.teacherName as teacherName " +
            " from CourseReviewDescribe as crd  " +
            " left JOIN Course  AS c ON c.courseId = crd.courseId " +
            " left join Teacher AS t on crd.teacherId = t.teacherId " +
            " left join StudentEntitys as s on crd.studentId = s.id " +
            " left join Classes as cs on s.classId = cs.classId " +
            " where crd.isValidated = '0' and c.isValidated = '0'  " +
            " and crd.courseId = ?1 " +
            " ORDER BY crd.createTime ")
    Page<ICourseReviewDto> findCourseReviewListByCourseIdOrderByCreateTime(String courseId, Pageable pageable);

}
