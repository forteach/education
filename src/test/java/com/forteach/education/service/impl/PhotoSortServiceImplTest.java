package com.forteach.education.service.impl;

import com.forteach.education.domain.PhotoSort;
import com.forteach.education.domain.Photos;
import com.forteach.education.service.PhotoSortService;
import com.forteach.education.web.vo.PhotoSortVo;
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
 * @Date: 18-11-27 09:47
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoSortServiceImplTest {

    @Autowired
    private PhotoSortService photoSortService;

    @Test
    public void save() {
        List<Photos> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(Photos.builder().photoDescription("图册_" + i)
                    .photoName("ppt_" + i)
                    .photoSrc("http://wx2.sinaimg.cn/large/006nLajtly1fk65lrevkqj30dw0dwadz.jpg")
//                    .sortImgId()
                    .build());
        }
        PhotoSort photoSort = photoSortService.save(PhotoSortVo.builder()
                .courceId("ff808181673e5e6c01673e5f792b0001")
                .imgPassword("123456")
                .sortImgName("图片名字")
                .topPicSrc("http://www.17qq.com/img_qqtouxiang/76490995.jpeg")
                .photos(list)
                .sortImgType(0)
                .build());
        log.info("photoSort : {}", photoSort.toString());
    }

    @Test
    public void findById() {
        PhotoSortVo photoSortVo = photoSortService.findById("ff8081816753ce30016753ce73360000");
        log.info("photoSort : {}", photoSortVo.toString());
    }

    @Test
    public void deleteById() {
        photoSortService.deleteById("ff8081816753d10a016753d148690000");
    }

    @Test
    public void edit() {
        photoSortService.edit(PhotoSort.builder()
                .sortImgId("ff808181675402fb0167540359a40000")
                .sortImgType(1)
                .build());
    }
}