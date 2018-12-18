package com.forteach.education.service.impl;

import com.forteach.education.databank.domain.ViewDatum;
import com.forteach.education.databank.service.ViewDatumService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/25 17:14
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewDatumServiceImplTest {

    @Autowired
    private ViewDatumService viewDatumService;
    @Test
    public void save() {
        ViewDatum viewDatum = viewDatumService.save(ViewDatum.builder()
                .chapterId("40288155674446a001674447bbe60000")
                .viewName("计算机发展史.flv")
                .viewType("flv")
                .viewUrl("https://www.bilibili.com/video/av6643537?from=search&seid=14324992629957613357")
                .build());
        log.info("viewDatum : {}", viewDatum.toString());
    }

    @Test
    public void findByChapterId() {
        List<ViewDatum> viewDatumList = viewDatumService.findByChapterId("40288155674446a001674447bbe60000");
        viewDatumList.forEach(viewDatum -> {
                log.info("viewDatum : {} \r\n", viewDatum.toString());
                }
        );
    }

    @Test
    public void findByCourseId() {
        List<ViewDatum> viewDatumList = viewDatumService.findViewDatumByCourseId("ff808181673e5e6c01673e5f792b0001");
        viewDatumList.forEach(viewDatum -> {
                    log.info("viewDatum : {} \r\n", viewDatum.toString());
                }
        );
    }

}