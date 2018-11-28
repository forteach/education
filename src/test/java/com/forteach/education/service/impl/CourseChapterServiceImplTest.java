package com.forteach.education.service.impl;

import com.forteach.education.service.CourseChapterService;
import com.forteach.education.web.resp.CourseTreeResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-28 13:31
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseChapterServiceImplTest {

    @Autowired
    private CourseChapterService courseChapterService;

    @Test
    public void findByCourseId() {
        List<CourseTreeResp> list = courseChapterService.findByCourseId("ff808181673e5e6c01673e5f792b0001");
        list.forEach(courseTreeResp -> {

        log.info("courseTreeResp : {}", courseTreeResp.toString());
        });
    }
}