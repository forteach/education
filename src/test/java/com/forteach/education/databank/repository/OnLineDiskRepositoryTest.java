package com.forteach.education.databank.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 11:18
 * @Version: v1.0
 * @Modified：
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OnLineDiskRepositoryTest {

    @Autowired
    private OnLineDiskRepository onLineDiskRepository;

    @Test
    public void save() {
//        OnLineDisk onLineDisk = new OnLineDisk();
//        onLineDisk.setFileName("电子商务的历史.docx");
//        onLineDisk.setFileSuffix("docx");
//        onLineDisk.setFileUrl("http://114.115.174.243:9001/test/pic/2020/10/14/d82629df41b14b9cbb265669c13860fe.docx");
//        onLineDisk.setUserId("123456");
//        onLineDisk.setUserName("用户名");
//        OnLineDisk save = onLineDiskRepository.save(onLineDisk);
//        Assert.assertNotNull(save);

//        OnLineDisk onLineDisk = new OnLineDisk();
//        onLineDisk.setFolder("资料");
//        onLineDisk.setUserId("123456");
//        onLineDisk.setUserName("用户名");
//        OnLineDisk save = onLineDiskRepository.save(onLineDisk);
//        Assert.assertNotNull(save);

//        OnLineDisk onLineDisk = new OnLineDisk();
//        onLineDisk.setFileName("测试视频.mp4");
//        onLineDisk.setFileSuffix("mp4");
//        onLineDisk.setFileUrl("http://114.115.174.243:9001/test/pic/2020/10/14/8fc4410971834b72a2bfae34f0df772a.mp4");
//        onLineDisk.setUserId("123456");
//        onLineDisk.setUserName("用户名");
//        OnLineDisk save = onLineDiskRepository.save(onLineDisk);
//        Assert.assertNotNull(save);
    }
}