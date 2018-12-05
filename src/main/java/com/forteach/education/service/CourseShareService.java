package com.forteach.education.service;

import com.forteach.education.dto.CourseShareTeacherDto;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-5 15:34
 * @Version: 1.0
 * @Description:
 */
public interface CourseShareService {
    /**
     * 根据课程科目ID查询对应的协作人员老师id和姓名
     * @param courseId
     * @return
     */
    List<CourseShareTeacherDto> selectCourseShareTeachersByCourseId(String courseId);
}
