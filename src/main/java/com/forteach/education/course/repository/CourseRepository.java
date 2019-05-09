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
    Page<ICourseListDto> findByCreateUserAndIsValidated(String cUser, String isValidated, Pageable pageable);

    /**
     * 分页查询课程信息根据课程id查询课程列表
     * @param classId
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Query(value = "SELECT  " +
            "c.courseId AS courseId, c.courseName AS courseName, c.topPicSrc AS topPicSrc, " +
            "c.courseDescribe AS courseDescribe, cjc.chapterId AS chapterId, cc.chapterName AS chapterName " +
            "FROM Course AS c " +
            "LEFT JOIN CourseJoinChapter AS cjc ON c.courseId = cjc.courseId " +
            "LEFT JOIN CourseChapter AS cc ON c.courseId = cc.courseId " +
            "WHERE c.isValidated = '0' AND c.courseId IN " +
            " (SELECT courseId FROM TeacherClassCourse WHERE classId = ?1) " +
            " ORDER BY c.createTime DESC")
    List<ICourseListDto> findByIsValidatedEqualsAndCourseIdInOrderByCreateTime(String classId);

}
