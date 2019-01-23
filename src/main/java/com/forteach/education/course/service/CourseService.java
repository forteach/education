package com.forteach.education.course.service;

import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.CourseImages;
import com.forteach.education.course.dto.ICourseListDto;
import com.forteach.education.course.web.req.CourseFindAllReq;
import com.forteach.education.course.web.res.CourseListResp;
import com.forteach.education.course.web.res.CourseResp;
import com.forteach.education.course.web.res.CourseSaveResp;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.course.web.req.CourseSaveReq;
import com.forteach.education.common.web.vo.SortVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:55
 * @Version: 1.0
 * @Description:
 */
public interface CourseService {

    public List<String> save(Course course, List<RTeacher> teachers);

    public String edit(Course course,String oldShareId,List<RTeacher> teachers);

    public void deleteIsValidById(String courseId);

    public void deleteById(String courseId);

    public void delete(Course course);

    public List<ICourseListDto> findAll(PageRequest page);

    public Map<String,Object> getCourseById(String courseId);

    public List<CourseImages> findImagesByCourseId(String courseId);

    public void saveCourseImages(CourseImagesReq courseImagesReq);

    public List<ICourseListDto> findMyCourse(String userId,PageRequest page);

    public Course getById(String id);

}
