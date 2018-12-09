package com.forteach.education.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-6 13:42
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class KNodeRepositoryImplTest {

    @Resource
    private RepositoryImpl kNodeRepository;
    @Test
    public void findKNodePage() {
//        kNodeRepository.findKNodePage(TAKE_EFFECT_OPEN, "")

    }
}