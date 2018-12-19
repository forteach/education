package com.forteach.education.course.service;


import com.forteach.education.course.dto.ICourseShareTeacherDto;
import com.forteach.education.course.dto.ICourseShareTeacherDto1;

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
    List<ICourseShareTeacherDto> selectCourseShareTeachersByCourseId(String courseId);

}
