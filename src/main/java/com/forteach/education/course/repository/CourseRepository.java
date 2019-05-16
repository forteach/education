package com.forteach.education.course.repository;

import com.forteach.education.course.domain.Course;
import com.forteach.education.course.dto.ICourseListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 16:29
 * @Version: 1.0
 * @Description:　科目
 */
public interface CourseRepository extends JpaRepository<Course, String> {

    public Course findByCourseId(String courseId);

    /**
     * 分页查询所有有效课程
     *
     * @param isValidated
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    Page<ICourseListDto> findByIsValidated(String isValidated, Pageable pageable);

    /**
     * 分页查询我的课程科目
     *
     * @param isValidated
     * @param cUser
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    Page<ICourseListDto> findByCreateUserAndIsValidatedOrderByCreateTimeDesc(String cUser, String isValidated, Pageable pageable);

    /**
     * 分页查询课程信息根据课程id查询课程列表
     * @param classId
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
//    @Query(value = "SELECT  " +
//            " c.courseId AS courseId, " +
//            " c.courseName AS courseName, " +
//            " c.topPicSrc AS topPicSrc, " +
//            " c.courseDescribe AS courseDescribe, " +
//            " ccc.chapterId AS chapterId, " +
//            " cc.chapterName AS chapterName " +
//            " FROM Course AS c " +
//            " LEFT JOIN CourseChapter AS cc ON c.courseId = cc.courseId " +
//            " LEFT JOIN CourseChapterCount AS ccc ON c.courseId = ccc.courseId " +
//            " AND cc.chapterId = ccc.chapterId " +
//            " AND ccc.classId = ?1 " +
//            " WHERE c.isValidated = '0' " +
//            " AND cc.isValidated = '0' " +
//            " AND c.courseId IN " +
//            " (SELECT DISTINCT tcc.courseId FROM TeacherClassCourse AS tcc " +
//            " WHERE tcc.isValidated = '0' AND  tcc.classId = ?1) " +
//            " ORDER BY c.createTime DESC ")

    @Query(value = "select " +
            " c.courseId as courseId, " +
            " c.courseName as courseName, " +
            " c.topPicSrc as topPicSrc, " +
            " c.courseDescribe as courseDescribe, " +
            " cc.chapterId as chapterId, " +
            " cc.chapterName as chapterName " +
            " from Course as c " +
            " left join CourseChapter as cc on c.courseId = cc.chapterId " +
            " left join CourseChapterCount as ccc on c.courseId = ccc.chapterId " +
            " and ccc.chapterId = cc.chapterId " +
            " and ccc.classId = ?1 " +
            " where c.isValidated = '0' " +
            " and cc.isValidated = '0' " +
            " and c.courseId in " +
            " (select distinct tcc.courseId from TeacherClassCourse as tcc where tcc.isValidated = '0' and tcc.classId = ?1) " +
            " order by c.createTime desc")
    List<ICourseListDto> findByIsValidatedEqualsAndCourseIdInOrderByCreateTime(String classId);
}
