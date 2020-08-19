package com.forteach.education.course.repository;

import com.forteach.education.classes.dto.IClassesDto;
import com.forteach.education.classes.dto.TeacherCourseDto;
import com.forteach.education.course.domain.TeacherClassCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-28 10:13
 * @version: 1.0
 * @description:
 */
public interface TeacherClassCourseRepository extends JpaRepository<TeacherClassCourse, String> {

    /**
     * 依创建时间根据班级id倒叙查询课程信息
     * @param classId
     * @return
     */
    @Query(value = "select courseId as courseId from TeacherClassCourse where isValidated = '0' and classId = ?1")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    List<String> findByClassId(String classId);

    /**
     * 教师id 查询所教的班级信息
     * @param teacherId
     * @return
     */
    @Query(value = " select " +
            " classId as classId, className as className from Classes " +
            " where isValidated = '0' and classId " +
            " in (select distinct classId from TeacherClassCourse where " +
            " isValidated = '0' and teacherId = ?1 and courseId in " +
            " (select distinct courseNumber from Course where isValidated = '0' and courseId = ?2 or ?2 is null or ?2 ='' )) ")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    List<IClassesDto> findClassInfoByTeacherId(String teacherId, String courseId);


    /**
     * 查询从信息数字话校园课程对应的教师信息
     * @param courseNumber 课程ID
     * @return List<TeacherCourseDto>
     */
    @Query(value = "select teacherId as teacherId, teacherName as teacherName from Teacher where isValidated = '0' and teacherId in " +
            " (select distinct teacherId from TeacherClassCourse where isValidated = '0' and courseId = ?1) ")
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    List<TeacherCourseDto> findTeacherByCourseId(String courseNumber);

    /**
     * 查询全部对应的课程信息
     * @return　List<TeacherCourseDto>
     */
    @Query(value = "select teacherId as teacherId, teacherName as teacherName from Teacher where isValidated = '0' and teacherId in " +
            " (select distinct teacherId from TeacherClassCourse where isValidated = '0')")
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    List<TeacherCourseDto> findTeacher();
}
