package com.forteach.education.service.impl;

import com.forteach.education.dto.CourseShareTeacherDto;
import com.forteach.education.repository.CourseShareRepository;
import com.forteach.education.service.CourseShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-5 15:36
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class CourseShareServiceImpl implements CourseShareService {

    @Resource
    private CourseShareRepository courseShareRepository;

    @Override
    public List<CourseShareTeacherDto> selectCourseShareTeachersByCourseId(String courseId) {
        return courseShareRepository.findTeachersByCourseId(courseId);
    }
}
