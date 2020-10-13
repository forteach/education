package com.forteach.education.controller;


import com.alibaba.fastjson.JSON;
import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.web.control.CourseChapterController;
import com.forteach.education.course.web.control.CourseController;
import com.forteach.education.course.web.req.CourseChapterReq;
import com.forteach.education.course.web.req.CourseFindAllReq;
import com.forteach.education.course.web.req.CourseSaveReq;
import com.forteach.education.course.web.vo.RCourse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.forteach.education.common.keyword.Dic.LESSON_PREPARATION_TYPE_GROUP;
import static com.forteach.education.common.keyword.Dic.LESSON_PREPARATION_TYPE_PRIVATE;


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
public class CourseControlTest {

    @Autowired
    private CourseController courseController;

    @Autowired
    private CourseChapterController courseChapterController;

    @Test
    public void save() {
        RCourse c = new RCourse();
        c.setCourseName("测试单人课程1-18");
        c.setCourseNumber("123456");
        c.setLessonPreparationType(LESSON_PREPARATION_TYPE_PRIVATE);//1、个人备课 2、集体备课
        c.setTeachingType("1，3");
        c.setTopPicSrc("http://118.24.120.43:8080/group1/M00/00/03/rBsADFwPZtGAIJdXAAFelcpPBsI922.jpg");
//
//        RTeacher t=new RTeacher();
//        t.setTeacherId("000");
//        t.setTeacherName("测试教师1");
//
//        RTeacher t2=new RTeacher();
//        t2.setTeacherId("001");
//        t2.setTeacherName("测试教师2");
//
//        List<RTeacher> list=new ArrayList<RTeacher>();
//        list.add(t);
//        list.add(t2);

        CourseSaveReq req = CourseSaveReq.builder()
                .course(c)
                //  .teachers(list)
                .createUser("000")
                .build();
        log.info("------*{}", JSON.toJSONString(req));
//        WebResult r = courseController.save(req);
//        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void edit() {

        RCourse c = new RCourse();
        c.setCourseId("2c91808d678e620701679bfccf570000");
        c.setCourseName("电子商务基础");
        c.setCourseNumber("123456");
        c.setLessonPreparationType(LESSON_PREPARATION_TYPE_GROUP);//1、个人备课 2、集体备课
        // c.setSpecialtyId("zhuanye001");
        c.setTeachingType("3");
        c.setTopPicSrc("http://118.24.120.43:8080/group1/M00/00/03/rBsADFwPZtGAIJdXAAFelcpPBsI922.jpg");

        RTeacher t = new RTeacher();
        t.setTeacherId("ff808181675ea68f01675ea6d86b0000");
        t.setTeacherName("测试");

        List<RTeacher> list = new ArrayList<RTeacher>();
        list.add(t);

        CourseSaveReq req = CourseSaveReq.builder()
                .course(c)
                .oldShareId("40288d5c67ed61250167ed635a280000")
                .teachers(list)
                .build();
        log.info("------*{}", JSON.toJSONString(req));
        WebResult r = courseController.edit(req);
        log.info("*********{}", JSON.toJSONString(r));
        log.info("ok");
    }

    @Test
    public void findAll() {
        CourseFindAllReq req = new CourseFindAllReq();
        SortVo sv = new SortVo();
        req.setSortVo(sv);
//        log.info("------*{}", JSON.toJSONString(req));
//        WebResult r = courseController.findAll(req);
//        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void findId() {
        String id = "{\"courseId\":\"2c9180846827407401682b57f4a60000\"}";
        log.info("------*{}", JSON.toJSONString(id));
        WebResult r = courseController.getCourseByCourseId(id);
        log.info("*********{}", JSON.toJSONString(r));
    }
//*****************************************************************

    @Test
    public void chapterSave() {
        CourseChapterReq req = new CourseChapterReq();
        req.setChapterParentId("0");
        req.setCourseId("40289fa0685e89b901685e8a100d0000");
        req.setChapterName("第一章");
        req.setCreateUser("000");
        log.info("------*{}", JSON.toJSONString(req));
//        WebResult r = courseChapterController.save(req);
//        log.info("*********{}", JSON.toJSONString(r));
    }

}