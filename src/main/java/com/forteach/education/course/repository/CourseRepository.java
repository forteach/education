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

//    /**
//     * 分页查询课程信息根据课程id查询课程列表
//     * @param classId
//     * @return
//     */
//    @Query(value = " select " +
//            "  c.courseId       as courseId, " +
//            "  c.courseName     as courseName, " +
//            "  c.alias          as alias, " +
//            "  c.topPicSrc     as topPicSrc, " +
//            "  c.courseDescribe as courseDescribe, " +
//            "  t.teacherId      as teacherId, " +
//            "  t.teacherName    as teacherName " +
//            " from Course as c " +
//            " left join TeacherClassCourse as tcc on c.courseNumber = tcc.courseId " +
//            " left join Teacher as t on t.teacherId= tcc.teacherId " +
//            " where c.isValidated = '0' " +
//            " and t.isValidated = '0' " +
//            " and tcc.isValidated = '0'" +
//            " and c.createUser = tcc.teacherId " +
//            " and tcc.classId = ?1 " +
//            " order by c.createTime ")
//    @Transactional(readOnly = true, rollbackFor = Exception.class)
//    List<ICourseChapterListDto> findByIsValidatedEqualsAndCourseIdInOrderByCreateTime(String classId);


    /**
     * 分页查询课程信息根据课程id查询课程列表
     *
     * @param classId
     * @return
     */
    @Query(value = " select " +
            " course_id as courseId, " +
            " alias as alias, " +
            " course_name as courseName, " +
            " course_describe as courseDescribe, " +
            " top_pic_src as topPicSrc, " +
            " v.c_user as teacherId, " +
            " teacher_name as teacherName," +
            " lesson_preparation_type AS lessonPreparationType " +
            " from " +
            " (SELECT course_id, alias,course_name, course_describe, top_pic_src, c_user,c_time, lesson_preparation_type " +
            " from course WHERE is_validated = '0' and course_number in " +
            " (SELECT course_id from teacher_class_course WHERE class_id = ?1)) as v" +
            " LEFT JOIN teacher as t on v.c_user = t.teacher_id ORDER BY v.c_time ", nativeQuery = true)
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    List<ICourseChapterListDto> findByIsValidatedEqualsAndCourseIdInOrderByCreateTime(String classId);

    /**
     * 查询有效的课程信息
     *
     * @param isValidated
     * @return
     */
    List<Course> findAllByIsValidated(String isValidated);
}
