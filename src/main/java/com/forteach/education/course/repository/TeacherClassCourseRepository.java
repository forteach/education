package com.forteach.education.course.repository;

import com.forteach.education.course.domain.TeacherAndClassAndCourse;
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
public interface TeacherClassCourseRepository extends JpaRepository<TeacherAndClassAndCourse, String> {

    /**
     * 依创建时间根据班级id倒叙查询课程信息
     * @param classId
     * @return
     */
    @Query(value = "select courseId as courseId from TeacherAndClassAndCourse where isValidated = '0' and classId = ?1")
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    List<String> findByClassId(String classId);
}
