package com.forteach.education.service.impl;


import com.forteach.education.course.service.CourseShareService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
//        List<CourseShareUsers> list = courseShareService.findByShareId("40288d5c67df334e0167df3393aa0001");
//        list.forEach(dto ->{
//            log.info("courseShareDto---1 : {}", dto.getUserName());
//        });
    }

}