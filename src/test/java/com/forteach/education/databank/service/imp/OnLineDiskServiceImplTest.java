package com.forteach.education.databank.service.imp;

import com.forteach.education.databank.domain.OnLineDisk;
import com.forteach.education.databank.service.OnLineDiskService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 18:25
 * @Version: v1.0
 * @Modified：
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OnLineDiskServiceImplTest extends TestCase {

    @Autowired
    private OnLineDiskService onLineDiskService;

    @Test
    public void testList() {
        List<OnLineDisk> list = onLineDiskService.list(0, "123456");
        list.forEach(System.out::println);
        Assert.assertNotNull(list);
    }

    @Test
    public void testDelete() {
        Set<Long> set = new HashSet<>();
        onLineDiskService.delete(set);
        Assert.assertTrue(true);
    }

    @Test
    public void testSave() {
        OnLineDisk onLineDisk = new OnLineDisk();
        onLineDisk.setFileName("测试视频.mp4");
        onLineDisk.setFileSuffix("mp4");
        onLineDisk.setFileUrl("http://114.115.174.243:9001/test/pic/2020/10/14/8fc4410971834b72a2bfae34f0df772a.mp4");
        onLineDisk.setUserId("123456");
        onLineDisk.setPId(3L);
        onLineDisk.setUserName("用户名");
        onLineDiskService.save(onLineDisk);
        Assert.assertTrue(true);
    }
}