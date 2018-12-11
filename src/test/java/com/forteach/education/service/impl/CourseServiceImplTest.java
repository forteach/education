package com.forteach.education.service.impl;

import com.forteach.education.domain.Course;
import com.forteach.education.domain.CourseImages;
import com.forteach.education.service.CourseService;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.web.vo.DataDatumVo;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-29 15:34
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;
    @Test
    public void saveCourseImages() {
        List<DataDatumVo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(DataDatumVo.builder()
                    .fileName("文件_" + i)
                    .filePath("https://www.bilibili.com/video/av6643537?from=search&seid=14324992629957613357")
                    .indexNum(i + 1)
                    .build());
        }
        courseService.saveCourseImages(
                CourseImagesReq.builder()
                        .courseId("ff808181673e5e6c01673e5f792b0001")
                        .images(list)
                        .build()
        );
    }

    @Test
    public void findImagesByCourseId() {
        List<CourseImages> imagesList = courseService.findImagesByCourseId("ff808181673e5e6c01673e5f792b0001");
        imagesList.forEach(courseImages -> {
            log.info("courseImages : {}", courseImages.toString());
        });
    }

    @Test
    public void findMyCourse() {
        SortVo sortVo = new SortVo();
        sortVo.setSorting("cTime");
        sortVo.setIsValidated("0");
        sortVo.setPage(0);
        sortVo.setSize(10);
        sortVo.setSort(0);
        Page<Course> page = courseService.findMyCourse(sortVo);
        page.forEach(course -> {
            log.info(course.toString() +"\r\n");
        });
    }
}