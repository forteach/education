package com.forteach.education.course.service;


import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.CourseShare;
import com.forteach.education.course.domain.CourseShareUsers;
import com.forteach.education.course.dto.ICourseShareTeacherDto;
import com.forteach.education.course.dto.ICourseShareTeacherDto1;
import com.forteach.education.course.web.res.CourseSaveResp;

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
     * 根据课程分享编号获得被分享用户列表
     * @param shareId
     * @return
     */
    public List<CourseShareUsers> findByShareId(String shareId);

    public String save( Course course, List<RTeacher> teacherList);

    public String update(String lessonPreType,String shareId, Course course, List<RTeacher> teacherList);

    public CourseShare findByAndCourseIdAndAndShareArea(String courseId );
}
