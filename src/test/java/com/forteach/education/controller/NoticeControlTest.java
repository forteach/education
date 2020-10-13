package com.forteach.education.controller;

import com.alibaba.fastjson.JSON;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.information.web.control.NoticeController;
import com.forteach.education.information.web.req.notice.ByIdNoticeRequest;
import com.forteach.education.information.web.req.notice.FindIsValListRequest;
import com.forteach.education.information.web.req.notice.SaveNoticeRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeControlTest {

    @Autowired
    private NoticeController noticeController;

    @Test
    public void save() {
        SaveNoticeRequest req = new SaveNoticeRequest();
        req.setNoticeId("");
        req.setContent("ok!!!!!");
        req.setArea("P");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = noticeController.save(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void findById() {
        ByIdNoticeRequest req = new ByIdNoticeRequest();
        req.setNoticeId("fbdcd8116389404f8e829d50ed67a6a3");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = noticeController.findById(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void findAll() {

        FindIsValListRequest req = new FindIsValListRequest();
        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = noticeController.findAll(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void deleteId() {
        ByIdNoticeRequest req = new ByIdNoticeRequest();
        req.setNoticeId("fbdcd8116389404f8e829d50ed67a6a3");

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = noticeController.deleteId(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

}
