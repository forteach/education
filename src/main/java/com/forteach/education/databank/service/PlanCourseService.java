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

    List<PlanCourse> findMyPlanCourse(String year, String semester, String teacherId);
}