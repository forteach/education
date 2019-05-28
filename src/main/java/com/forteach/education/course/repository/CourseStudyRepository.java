package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseStudy;
import com.forteach.education.course.dto.ICourseStudyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-27 15:27
 * @version: 1.0
 * @description: 课程学习数据
 */
public interface CourseStudyRepository extends JpaRepository<CourseStudy, String> {
    /**
     * 查询有效的学习状态课程信息
     * @param isValidated
     * @param courseId
     * @param studentId
     * @return
     */
//    List<CourseStudy> findByIsValidatedEqualsAndCourseIdAndStudentId(String isValidated, String courseId, String studentId);

    /**
     * 查询学生学习课程的状态信息
     * @param studentId
     * @return
     */
    @Query(value = "select " +
            " c.courseId as courseId, " +
            " c.courseName as courseName, " +
            " cc.chapterId as chapterId, " +
            " cc.chapterName as chapterName, " +
            " cs.studyStatus as studyStatus, " +
            " cs.semesterGrade as semesterGrade," +
            " cs.examGrade as examGrade, " +
            " cs.examResults as examResults, " +
            " cs.makeUpExamination as makeUpExamination " +
            " from CourseStudy as cs " +
            " left join Course as c on c.courseId = cs.courseId " +
            " left join CourseChapter cc on cc.chapterId = cs.chapterId " +
            " where cs.isValidated = '0' and cs.studentId = ?1 " +
            " and cs.studyStatus = ?2 OR ?2 IS NULL OR ?2 ='' " +
            " order by cs.createTime desc")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    List<ICourseStudyDto> findByIsValidatedEqualsAndStudentId(String studentId, Integer studyStatus);
}
