package com.forteach.education.service;

import com.forteach.education.domain.Course;
import com.forteach.education.web.vo.SortVo;
import org.springframework.data.domain.Page;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:55
 * @Version: 1.0
 * @Description:
 */
public interface CourseService {

    Course save(Course course);

    void deleteById(String courseId);

    void delete(Course course);

    Course edit(Course course);

    void deleteIsValidById(String courseId);

    Page<Course> findAll(SortVo sortVo);

    Course getCourseById(String courseId);

}
