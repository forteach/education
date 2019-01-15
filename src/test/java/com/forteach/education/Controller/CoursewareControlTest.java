package com.forteach.education.Controller;


import com.alibaba.fastjson.JSON;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.web.control.CoursewareController;
import com.forteach.education.course.web.req.CoursewareAll;
import com.forteach.education.course.web.req.FindImpCoursewareReq;
import com.forteach.education.course.web.req.ImpCoursewareAll;
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
public class CoursewareControlTest {

    @Autowired
    private CoursewareController coursewareController;

    @Test
    public void save(){

        ImpCoursewareAll req=new ImpCoursewareAll();
        req.setChapterId("40288d5c682b825e01682b8442630001");
        req.setDatumType("1");
        req.setImportantType("1");

        CoursewareAll ca=new CoursewareAll();
        ca.setChapterId("40288d5c682b825e01682b8442630001");
        ca.setFileName("test.jpg");
        ca.setFileUrl("http://118.24.120.43:8080/group1/M00/00/03/rBsADFw1nxyAaHgHAAoNCP4-KtI739.jpg");

        List<CoursewareAll> list=new ArrayList<CoursewareAll>();
        list.add(ca);
        req.setFiles(list);

        log.info("*********{}" , JSON.toJSONString(req));
        WebResult r=coursewareController.save(req);
        log.info("*********{}" ,JSON.toJSONString(r));
        log.info("ok");
    }

    @Test
    public void saveAtlit(){

        ImpCoursewareAll req=new ImpCoursewareAll();
        req.setChapterId("40288d5c682b825e01682b8442630001");
        req.setPhotoDatumName("测试图集");

        CoursewareAll ca=new CoursewareAll();
        ca.setChapterId("40288d5c682b825e01682b8442630001");
        ca.setFileName("test1.jpg");
        ca.setFileUrl("http://118.24.120.43:8080/group1/M00/00/03/rBsADFw1nxyAaHgHAAoNCP4-KtI739.jpg");

        List<CoursewareAll> list=new ArrayList<CoursewareAll>();
        list.add(ca);
        req.setFiles(list);

        log.info("*********{}" , JSON.toJSONString(req));
        WebResult r=coursewareController.saveCourseAtlit(req);
        log.info("*********{}" ,JSON.toJSONString(r));
        log.info("ok");
    }

    @Test
    public void findByfindByChapterId() {
//        String id="{\"chapterId\":\"40288d5c682b825e01682b8442630001\"}";
        FindImpCoursewareReq req=new FindImpCoursewareReq();
        req.setChapterId("40288d5c682b825e01682b8442630001");
        req.setDatumType("1");
        req.setImportantType("1");
        log.info("*********{}" , JSON.toJSONString(req));
        WebResult r=coursewareController.getImpCourseware(req);
        log.info("*********{}" , JSON.toJSONString(r));
    }

    @Test
    public void getCourseArlitsList() {
       String id="{\"chapterId\":\"40288d5c682b825e01682b8442630001\"}";
        WebResult r=coursewareController.getCourseArlitsList(id);
        log.info("*********{}" , JSON.toJSONString(r));
    }

    @Test
    public void getPhotoList() {
        String id="{\"arlitId\":\"c3d277934faf4abcb03d09edcce1307f\"}";
        WebResult r=coursewareController.getPhotoList(id);
        log.info("*********{}" , JSON.toJSONString(r));
    }
}