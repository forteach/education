package com.forteach.education.service.impl;

import com.forteach.education.domain.ChapteData;
import com.forteach.education.service.ChapteDataService;
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
 * @Date: 18-11-26 11:09
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChapteDataServiceImplTest {

    @Autowired
    private ChapteDataService chapteDataService;
    @Test
    public void findByChapterId() {

        List<ChapteData> chapteDataList = chapteDataService.findByChapterId("ff808181673e9f6801673ea011950000");
        chapteDataList.forEach(chapteData -> {
                log.info("ChapteData : {}", chapteData.toString());
                }
        );
    }

    @Test
    public void findByCourseId() {
        List<ChapteData> chapteDataList = chapteDataService.findByCourseId("ff808181673e5e6c01673e5f792b0001");
        chapteDataList.forEach(chapteData -> {
            log.info("chapteData : {} \r\n", chapteData.toString());
        });
    }

    @Test
    public void save() {
        ChapteData chapteData = chapteDataService.save(
                ChapteData.builder().courseId("ff808181673e5e6c01673e5f792b0001").chapterId("ff808181673e9f6801673ea011950000")
                        .fileId("40288155674a405d01674a407ef20000")
                        .audioId("402881556749ef47016749ef66af0000")
                        .datumName("计算机资料")
                        .linkId("40288155674a380e01674a382f780000")
                        .viewId("40288155674a31d101674a31f1e30000")
                        .sortImgId("")
                        .build()
        );
        log.info("ChapteData : {}", chapteData.toString());
    }

    @Test
    public void edit() {
        ChapteData chapteData = chapteDataService.findById("ff808181674ee66201674ee6aff50000");
        chapteData.setCourseId("ff808181673e5e6c01673e5f792b0001");
        chapteDataService.edit(chapteData);
    }
}