package com.forteach.education.databank.repository;


import com.forteach.education.databank.domain.PlanCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/8 16:48
 * @Version: v1.0
 * @Modified：排课信息
 * @Description:
 */
public interface PlanCourseRepository extends JpaRepository<PlanCourse, String> {

    /**
     * 查询教师课程表信息
     *
     * @param isValidated
     * @param year
     * @param semester
     * @param teacherId
     * @return
     */
    @Transactional(readOnly = true)
    List<PlanCourse> findAllByIsValidatedEqualsAndYearAndSemesterAndTeacherId(String isValidated, String year, String semester, String teacherId);

    /**
     * 查询班级对应的课程表信息
     * @param isValidated
     * @param year
     * @param semester
     * @param classId
     * @return
     */
    @Transactional(readOnly = true)
    List<PlanCourse> findAllByIsValidatedEqualsAndYearAndSemesterAndClassId(String isValidated, String year, String semester, String classId);
}