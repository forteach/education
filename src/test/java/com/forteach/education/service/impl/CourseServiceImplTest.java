package com.forteach.education.service.impl;


import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.CourseImages;
import com.forteach.education.course.service.CourseService;
import com.forteach.education.course.web.req.CourseSaveReq;
import com.forteach.education.course.web.vo.RCourse;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.web.vo.DataDatumVo;
import com.forteach.education.common.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.keyword.Dic.LESSON_PREPARATION_TYPE_GROUP;

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
    public void save(){
        RCourse c=new RCourse();
        c.setCourseName("测试课程2");
        c.setCourseNumber("123456");
        c.setLessonPreparationType(LESSON_PREPARATION_TYPE_GROUP);//1、个人备课 2、集体备课
       // c.setSpecialtyId("zhuanye001");
        c.setTeachingType("3");
        c.setTopPicSrc("http://118.24.120.43:8080/group1/M00/00/03/rBsADFwPZtGAIJdXAAFelcpPBsI922.jpg");

        RTeacher t=new RTeacher();
        t.setTeacherId("ff808181675ea68f01675ea6d86b0000");
        t.setTeacherName("测试");

        List<RTeacher> list=new ArrayList<RTeacher>();
        list.add(t);

        CourseSaveReq req=CourseSaveReq.builder()
                .course(c)
                .teachers(list)
                .build();
       // courseService.save(req);
    }


    @Test
    public void saveCourseImages() {
        List<DataDatumVo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(DataDatumVo.builder()
                    .fileName("文件_" + i)
                    .fileUrl("https://www.bilibili.com/video/av6643537?from=search&seid=14324992629957613357")
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
//        Page<Course> page = courseService.findMyCourse(sortVo);
//        page.forEach(course -> {
//            log.info(course.toString() +"\r\n");
//        });
    }
}