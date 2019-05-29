package com.forteach.education.controller;

import com.alibaba.fastjson.JSON;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.web.control.CourseDataController;
import com.forteach.education.course.web.vo.RCourseData;
import com.forteach.education.databank.web.control.ChapteDataController;
import com.forteach.education.databank.web.req.ChapteDataListReq;
import com.forteach.education.databank.web.req.ChapteDataReq;
import com.forteach.education.web.req.CourseDataDatumReq;
import com.forteach.education.web.vo.DataDatumVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class CourseDateControlTest {

    @Autowired
    private ChapteDataController chapteDataController;

    @Autowired
    private CourseDataController courseDataController;


    @Test
    public void save() {
        ChapteDataReq req = new ChapteDataReq();
        req.setChapterId("40289fa0685ea88101685ea8c2cd0000");
        req.setDatumName("");
        req.setDatumArea("3,4,5");
        req.setDatumType("1");
        req.setCourseId("2c9180846827407401682b57f4a60000");
        req.setCreateUser("000");

        DataDatumVo dv = new DataDatumVo();
        dv.setFileName("课程资料添加测试文件");
        dv.setFileUrl("http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.jpg");

        DataDatumVo dv1 = new DataDatumVo();
        dv1.setFileName("课程资料添加测试文件1");
        dv1.setFileUrl("http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.jpg");


        List<DataDatumVo> list = new ArrayList<DataDatumVo>();
        list.add(dv);
        list.add(dv1);
        req.setFiles(list);

        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = chapteDataController.save(req);
        log.info("*********{}", JSON.toJSONString(r));

    }

    @Test
    public void findDataList() {
        ChapteDataListReq req = new ChapteDataListReq();
        req.setChapterId("40288d5c682b825e01682b8442630001");
        req.setCourseId("2c9180846827407401682b57f4a60000");
        req.setDatumArea("3,4,5,6");
        req.setDatumType("1");
        SortVo sv = new SortVo();
        req.setSortVo(sv);
        log.info("request-----{}", JSON.toJSONString(req));
        WebResult r = chapteDataController.findDatumList(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void saveBeike() {
        CourseDataDatumReq req = new CourseDataDatumReq();
        req.setChapterId("40289fa0685ea88101685ea8c2cd0000");
        req.setCourseId("2c9180846827407401682b57f4a60000");
        req.setCreateUser("000");

        RCourseData rd = new RCourseData();
        rd.setChapterId("40289fa0685ea88101685ea8c2cd0000");
        rd.setDatumArea("3,4,5");
        rd.setDatumType("1");
        rd.setKNodeId("1234");
        rd.setDatumName("备课资料挂接文件");
        rd.setDatumUrl("http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.jpg");


        List<RCourseData> list = new ArrayList<RCourseData>();
        list.add(rd);

        req.setFiles(list);
        log.info("------*{}", JSON.toJSONString(req));
        WebResult r = courseDataController.save(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

    @Test
    public void findBeike() {
        ChapteDataListReq req = new ChapteDataListReq();
        req.setChapterId("40289fa0685ea88101685ea8c2cd0000");
        req.setDatumType("1");
        SortVo sv = new SortVo();
        req.setSortVo(sv);
        log.info("------*{}", JSON.toJSONString(req));
        WebResult r = courseDataController.findDatumList(req);
        log.info("*********{}", JSON.toJSONString(r));
    }

}