package com.forteach.education.service.impl;

import com.forteach.education.course.dto.CourseShareTeacherDto;
import com.forteach.education.course.service.CourseShareService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-5 15:38
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseShareServiceImplTest {

    @Resource
    private CourseShareService courseShareService;
    @Test
    public void selectCourseShareTeachersByCourseId() {
        List<CourseShareTeacherDto> list = courseShareService.selectCourseShareTeachersByCourseId("ff808181677d238701677d26fdae0002");
        list.forEach(dto ->{
            log.info("courseShareDto : {}", dto.toString());
        });
    }
}