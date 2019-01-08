package com.forteach.education.Controller;


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
    public void save(){

        KNodeAll req=new KNodeAll();
        req.setChapterId("2c9180c067ee2be40167eeb29a7f0004");
        req.setCourseId("2c9180846827407401682b57f4a60000");
        req.setNodeName("12345");
        log.info("*********{}" , JSON.toJSONString(req));
        WebResult r=kNodeController.save(req);
        log.info("*********{}" ,JSON.toJSONString(r));
        log.info("ok");
    }

    @Test
    public void findByfindByChapterId() {
        String id="{\"chapterId\":\"2c9180c067ee2be40167eeb29a7f0004\"}";
        log.info("*********{}" , JSON.toJSONString(id));
        WebResult r=kNodeController.findByChapter(id);
        log.info("*********{}" , JSON.toJSONString(r));
    }
}