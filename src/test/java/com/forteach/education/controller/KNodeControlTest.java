package com.forteach.education.controller;


import com.alibaba.fastjson.JSON;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.web.control.KNodeController;
import com.forteach.education.course.web.req.KNodeAll;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void save() {

        KNodeAll req = new KNodeAll();
        req.setChapterId("40289fa0685ea88101685ea8c2cd0000");
        req.setCourseId("40289fa0685e89b901685e8a100d0000");
        req.setNodeName("测试A");
        req.setCreateUser("000");
        log.info("*********{}", JSON.toJSONString(req));
        WebResult r = kNodeController.save(req);
        log.info("*********{}", JSON.toJSONString(r));
        log.info("ok");
    }

    @Test
    public void findByfindByChapterId() {
        String id = "{\"chapterId\":\"40289fa0685ea88101685ea8c2cd0000\"}";
        log.info("*********{}", JSON.toJSONString(id));
        WebResult r = kNodeController.findByChapter(id);
        log.info("*********{}", JSON.toJSONString(r));
    }
}