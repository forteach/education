package com.forteach.education.service;

import com.forteach.education.domain.Course;
import com.forteach.education.domain.CourseImages;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.web.req.CourseReq;
import com.forteach.education.web.vo.SortVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:55
 * @Version: 1.0
 * @Description:
 */
public interface CourseService {

    Course save(CourseReq courseReq);

    void deleteById(String courseId);

    void delete(Course course);

    Course edit(Course course);

    void deleteIsValidById(String courseId);

    Page<Course> findAll(SortVo sortVo);

    Course getCourseById(String courseId);

    List<CourseImages> findImagesByCourseId(String courseId);

    void saveCourseImages(CourseImagesReq courseImagesReq);

}
