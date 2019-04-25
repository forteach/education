package com.forteach.education.controller;

import com.alibaba.fastjson.JSON;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.web.control.ArticleCommentController;
import com.forteach.education.information.web.control.ArticleController;
import com.forteach.education.information.web.req.artComment.AddCommentGoodRequest;
import com.forteach.education.information.web.req.artComment.FindArticleIdRequest;
import com.forteach.education.information.web.req.artComment.SaveArtCommentRequest;
import com.forteach.education.information.web.req.artComment.SaveReplyRequest;
import com.forteach.education.information.web.req.article.AddClickGoodRequest;
import com.forteach.education.information.web.req.article.ByIdRequest;
import com.forteach.education.information.web.req.article.FindAllRequest;
import com.forteach.education.information.web.req.article.SaveArticleRequest;
import com.forteach.education.information.web.req.myArticle.DeleteMyArticleRequest;
import com.forteach.education.web.vo.DataDatumVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtcileCommentControlTest {

    @Autowired
    private ArticleCommentController articleController;

    @Test
    public void save() {
        SaveArtCommentRequest req = new SaveArtCommentRequest();
        req.setArticleId("fc26d5f13e3746e9995d9c51f822f819");
        req.setUserId("1301331992031827761");
        req.setContent("ok!!!!!");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.save(req);
        log.info("*********{}", JSON.toJSONString(r));
    }


    @Test
    public void findArticleId() {

        FindArticleIdRequest req = new FindArticleIdRequest();
        req.setArticleId("fc26d5f13e3746e9995d9c51f822f819");
        SortVo sv = new SortVo();
        req.setSortVo(sv);

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.findArticleComment(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void addGood() {

        AddCommentGoodRequest req = new AddCommentGoodRequest();
        req.setCommentId("7a938c2f79054b0788ff87e64cedaa87");
        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.addClickGood(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void saveReply() {

        SaveReplyRequest req = new SaveReplyRequest();
        req.setCommentId("7a938c2f79054b0788ff87e64cedaa87");
        req.setReply("reply test");
        req.setReplyUserName("test");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.saveReply(req);
        log.info("*********{}", JSON.toJSONString(r));
    }
}
