package com.forteach.education.controller;

import com.alibaba.fastjson.JSON;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.web.control.ArticleController;
import com.forteach.education.information.web.req.article.*;
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
public class ArtcileControlTest {

    @Autowired
    private ArticleController articleController;

    @Test
    public void save() {
        SaveArticleRequest req = new SaveArticleRequest();
//        req.setArticleId("29512c44cb704629a9de547528ead313");
        req.setCourseId("071dac174343493cbd1b7ed3740a2ece");
        req.setUserId("130133199203182776");
        req.setTitle("第一节心得");
        req.setClassId("000016");
//        req.setImgUrl("标题图片URL");
        req.setDescription("文章描述");
        req.setArticleConten("文章内容111");
        req.setArticleType("xd");

        DataDatumVo dv = new DataDatumVo();
        dv.setFileName("资料图片文件1");
        dv.setFileUrl("http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.jpg");

        DataDatumVo dv1 = new DataDatumVo();
        dv1.setFileName("资料图片文件2");
        dv1.setFileUrl("http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.jpg");


        List<DataDatumVo> list = new ArrayList<DataDatumVo>();
        list.add(dv);
        list.add(dv1);
        req.setImages(list);

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.save(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void byId() {
        ByIdRequest req = new ByIdRequest();
        req.setId("f54d55f7a068459c820bf243b8300907");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.findById(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void findAllDesc() {

        FindAllRequest req = new FindAllRequest();
       // req.setStudentId("130133199203182776");
       // req.setCourseId("2c9180846827407401682b57f4a60000");
        SortVo sv = new SortVo();
        req.setSortVo(sv);

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.findAllDesc(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void findStuAllDesc() {

        FindAllRequest req = new FindAllRequest();
         req.setUserId("130133199203182776");
        // req.setCourseId("2c9180846827407401682b57f4a60000");
        SortVo sv = new SortVo();
        req.setSortVo(sv);

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.findStuAllDesc(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void addClickGood() {

        AddClickGoodRequest req = new AddClickGoodRequest();
        req.setArticleId("8044b4c211fc483dbed232a389c54d52");
        req.setUserId("1301331992031827761");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.addClickGood(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void addNice() {

        AddNiceRequest req = new AddNiceRequest();
        req.setArticleId("6bb9f828b87d4660b4d4b5947a17c96a");
        req.setValue("true");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.addNice(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void deleteCollect() {

        DeleteMyArticleRequest req = new DeleteMyArticleRequest();
        req.setArticleId("8044b4c211fc483dbed232a389c54d52");
        req.setUserId("1301331992031827761");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = articleController.deleteCollect(req);
        log.info("*********{}", JSON.toJSONString(r));
    }
}
