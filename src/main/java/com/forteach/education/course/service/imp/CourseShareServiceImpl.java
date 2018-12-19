package com.forteach.education.course.service.imp;


import com.forteach.education.course.dto.ICourseShareTeacherDto;
import com.forteach.education.course.dto.ICourseShareTeacherDto1;
import com.forteach.education.course.repository.CourseShareRepository;
import com.forteach.education.course.service.CourseShareService;
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
    public List<ICourseShareTeacherDto> selectCourseShareTeachersByCourseId(String courseId) {
        return courseShareRepository.findTeachersByCourseId(courseId);
    }


}
