package com.forteach.education.Controller;


import com.alibaba.fastjson.JSON;
import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.web.control.CourseController;
import com.forteach.education.course.web.control.KNodeController;
import com.forteach.education.course.web.req.CourseFindAllReq;
import com.forteach.education.course.web.req.CourseSaveReq;
import com.forteach.education.course.web.req.KNodeAll;
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
public class KNodeControlTest {

    @Autowired
    private KNodeController kNodeController;

    @Test
    public void save(){

        KNodeAll req=new KNodeAll();
        req.setChapterId("2c9180c067ee2be40167eeb29a7f0004");
        req.setCourseId("40288d5c67ed87b80167ed9569ed0000");
        req.setNodeName("12345");
        WebResult r=kNodeController.save(req);
        log.info("*********{}" ,JSON.toJSONString(r));
    }

    @Test
    public void findByfindByChapterId() {
        String id="{\"chapterId\":\"2c91809267462308016746293a2a0002\"}";
        WebResult r=kNodeController.findByChapter(id);
        log.info("*********{}" , JSON.toJSONString(r));
    }
}