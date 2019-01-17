package com.forteach.education.Controller;


import com.alibaba.fastjson.JSON;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.web.control.CourseDataController;
import com.forteach.education.course.web.vo.RCourseData;
import com.forteach.education.databank.web.control.ChapteDataController;
import com.forteach.education.databank.web.req.ChapteDataCountReq;
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
public class CharteDateControlTest {

    @Autowired
    private ChapteDataController chapteDataController;

    @Autowired
    private CourseDataController courseDataController;


        @Test
    public void save(){
        ChapteDataReq req=new ChapteDataReq();
        req.setChapterId("40288d5c682b825e01682b8442630001");
        req.setDatumName("");
        req.setDatumArea("3,4,5");
        req.setDatumType("1");

        DataDatumVo dv=new DataDatumVo();
        dv.setFileName("工作汇报2");
        dv.setFilePath("http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.jpg");

        List<DataDatumVo> list=new ArrayList<DataDatumVo>();
        list.add(dv);

        req.setFiles(list);

        log.info("request-----{}" , JSON.toJSONString(req));
        WebResult r=chapteDataController.save(req);
        log.info("*********{}" , JSON.toJSONString(r));

    }

    @Test
    public void findDataList(){
        ChapteDataListReq req=new ChapteDataListReq();
        req.setChapterId("40288d5c682b825e01682b8442630001");
        req.setDatumArea("2");
        req.setDatumType("1");
        SortVo sv=new SortVo();
        req.setSortVo(sv);
        log.info("request-----{}" , JSON.toJSONString(req));
        WebResult r=chapteDataController.findDatumList(req);
        log.info("*********{}" , JSON.toJSONString(r));
    }

    @Test
    public void saveBeike(){
        CourseDataDatumReq req=new CourseDataDatumReq();
        req.setChapterId("40288d5c682b825e01682b8442630002");
        req.setCourseId("40288d5c682b825e01682b8442630001");

        RCourseData rd=new RCourseData();
        rd.setChapterId("40288d5c682b825e01682b8442630002");
        rd.setDatumArea("3,4,5");
        rd.setDatumType("1");
        rd.setKNodeId("1234");


        List<RCourseData> list=new ArrayList<RCourseData>();
        list.add(rd);

        req.setFiles(list);
        log.info("------*{}" ,JSON.toJSONString(req));
        WebResult r=courseDataController.save(req);
        log.info("*********{}" ,JSON.toJSONString(r));
    }

    @Test
    public void findBeike(){
        ChapteDataListReq req=new ChapteDataListReq();
        req.setChapterId("40288d5c682b825e01682b8442630001");
        req.setDatumArea("2");
        req.setDatumType("1");
        SortVo sv=new SortVo();
        req.setSortVo(sv);
        log.info("------*{}" ,JSON.toJSONString(req));
        WebResult r=courseDataController.findDatumList(req);
        log.info("*********{}" ,JSON.toJSONString(r));
    }


}