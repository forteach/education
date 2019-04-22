package com.forteach.education.controller;

import com.alibaba.fastjson.JSON;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.web.control.ArticleController;
import com.forteach.education.information.web.req.article.ByIdRequest;
import com.forteach.education.information.web.req.article.FindAllRequest;
import com.forteach.education.information.web.req.article.SaveArticleRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtcileControlTest {

    @Autowired
    private ArticleController articleController;

    @Test
    public void save() {
        SaveArticleRequest req = new SaveArticleRequest();
        req.setArticleId("29512c44cb704629a9de547528ead313");
        req.setCourseId("2c9180846827407401682b57f4a60000");
        req.setUserId("02");
        req.setTitle("第一节心得");
        req.setClassId("classId");
        req.setImgUrl("标题图片URL");
        req.setDescription("文章描述");
        req.setArticleConten("文章内容");
        req.setArticleType("xd");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.save(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void byId() {
        ByIdRequest req = new ByIdRequest();
        req.setId("29512c44cb704629a9de547528ead313");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.findById(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void findAllDesc() {

        FindAllRequest req = new FindAllRequest();
        req.setStudentId("02");
        req.setCourseId("2c9180846827407401682b57f4a60000");
        SortVo sv = new SortVo();
        req.setSortVo(sv);

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.findAllDesc(req);
        log.info("*********{}", JSON.toJSONString(r));
    }
}
