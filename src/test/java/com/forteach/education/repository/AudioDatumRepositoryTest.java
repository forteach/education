package com.forteach.education.repository;

import com.forteach.education.domain.AudioDatum;
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
 * @Date: 2018/11/25 15:51
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AudioDatumRepositoryTest {

    @Autowired
    private AudioDatumRepository audioDatumRepository;
    @Test
    public void findAllByIsValidatedEqualsAndAndChapterId() {
        String chapterId = "40288155674446a001674447bbe60000";
        List<AudioDatum> list = audioDatumRepository.findByIsValidatedEqualsAndChapterId("0", chapterId);
        list.forEach(audioDatum -> {
            log.info("audioDatum : {}", audioDatum.toString());
        });
    }

    @Test
    public void save(){
        audioDatumRepository.save(AudioDatum.builder()
                .audioName("计算机简介.mp3").audioType("mp3").chapterId("40288155674446a001674447bbe60000")
                .audioUrl("http://sc1.111ttt.cn/2018/1/03/13/396131229550.mp3")
                .build());
    }
}