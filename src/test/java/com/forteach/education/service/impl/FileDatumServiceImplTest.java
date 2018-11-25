package com.forteach.education.service.impl;

import com.forteach.education.domain.FileDatum;
import com.forteach.education.service.FileDatumService;
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
 * @Date: 2018/11/25 17:15
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileDatumServiceImplTest {

    @Autowired
    private FileDatumService fileDatumService;

    @Test
    public void save() {
        FileDatum fileDatum = fileDatumService.save(
                FileDatum.builder().chapterId("40288155674446a001674447bbe60000").fileName("计算机史.ppt")
                        .fileUrl("https://www.bilibili.com/video/av449008?from=search&seid=14888223291771356300").build()
        );
        log.info("fileDatum : ", fileDatum.toString());
    }

    @Test
    public void findByChapterId() {
        List<FileDatum> list = fileDatumService.findByChapterId("40288155674446a001674447bbe60000");
        list.forEach(fileDatum -> {
            log.info("file : {}", fileDatum.toString());
        });
    }
}