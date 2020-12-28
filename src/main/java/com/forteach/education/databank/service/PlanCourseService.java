package com.forteach.education.databank.service;

import com.forteach.education.databank.domain.PlanCourse;

import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/8 17:11
 * @Version: v1.0
 * @Modified：课程表计划
 * @Description:
 */
public interface PlanCourseService {

    /**
     * 教师端查询课程表信息
     *
     * @param year      当前年份
     * @param semester  当前学期
     * @param teacherId 教师的编号
     * @return
     */
    List<PlanCourse> findMyPlanCourse(String year, String semester, String teacherId);

    /**
     * 学生端查询课程表信息
     *
     * @param year     当前年份
     * @param semester 当前学期
     * @param classId  班级Id
     * @return
     */
    List<PlanCourse> findMyPlanCourseByClassId(String year, String semester, String classId);
}