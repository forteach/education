package com.forteach.education.course.repository;

import com.forteach.education.course.domain.Course;
import com.forteach.education.course.dto.ICourseChapterListDto;
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
    @Query(value = " select " +
            "  c.courseId       as courseId, " +
            "  c.courseName     as courseName, " +
            "  c.alias          as alias, " +
            "  c.topPicSrc     as topPicSrc, " +
            "  c.courseDescribe as courseDescribe, " +
            "  t.teacherId      as teacherId, " +
            "  t.teacherName    as teacherName " +
            " from Course as c " +
            " left join TeacherClassCourse as tcc on c.courseNumber = tcc.courseId " +
            " left join Teacher as t on t.teacherId= tcc.teacherId " +
            " where c.isValidated = '0' " +
            " and t.isValidated = '0' " +
            " and tcc.isValidated = '0'" +
            " and c.createUser = tcc.teacherId " +
            " and tcc.classId = ?1 " +
            " order by c.createTime ")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    List<ICourseChapterListDto> findByIsValidatedEqualsAndCourseIdInOrderByCreateTime(String classId);
}
