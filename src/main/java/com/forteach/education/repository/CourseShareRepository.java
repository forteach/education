package com.forteach.education.repository;

import com.forteach.education.domain.CourseShare;
import com.forteach.education.dto.CourseShareTeacherDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:37
 * @Version: 1.0
 * @Description: 分享范围 1、 全部 2、 章节
 */
public interface CourseShareRepository extends JpaRepository<CourseShare, String> {

    int deleteByCourseId(String courseId);

    List<CourseShare> findByCourseId(String courseId);

    //select c.teacher_id as teacher_id, t.teacher_name as teacher_name from course_share as c, teacher as t
    //where c.is_validated = '0'
    //  and c.teacher_id = t.teacher_id
    //  and c.course_id = :?
//    @Query("select new com.forteach.education.dto.CourseShareTeacherDto(teacherId, teacherName) from CourseShare, Teacher" +
//            " where CourseShare.isValidated = '0' and CourseShare.teacherId = Teacher.teacherId" +
//            "  and CourseShare.courseId = ?1")
    @Query(value = "select new com.forteach.education.dto.CourseShareTeacherDto" +
            "(c.teacherId AS teacherId, t.teacherName AS teacherName) from CourseShare AS c left join Teacher AS t on c.teacherId = t.teacherId where " +
            "c.isValidated = '0' AND c.courseId = :courseId")
    List<CourseShareTeacherDto> findTeachersByCourseId(@Param("courseId") String courseId);
}
