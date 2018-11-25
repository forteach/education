package com.forteach.education.service.impl;

import com.forteach.education.domain.LinkDatum;
import com.forteach.education.service.LinkDatumService;
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
public class LinkServiceImplTest {

    @Autowired
    private LinkDatumService linkDatumService;
    @Test
    public void save() {
        LinkDatum linkDatum = linkDatumService.save(LinkDatum.builder()
                        .chapterId("40288155674446a001674447bbe60000").linkName("计算百科")
                        .linkUrl("https://baike.baidu.com/item/%E8%AE%A1%E7%AE%97%E6%9C%BA/140338?fr=aladdin")
                        .build());
        log.info("LinkDatum : {}", linkDatum.toString());
    }

    @Test
    public void findByChapterId() {
        List<LinkDatum> list = linkDatumService.findByChapterId("40288155674446a001674447bbe60000");
        list.forEach(linkDatum -> {
            log.info("linkDatum : {}", linkDatum.toString());
        });
    }
}