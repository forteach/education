package com.forteach.education.authority.repository;

import com.forteach.education.authority.domain.StudentEntitys;
import com.forteach.education.common.keyword.Dic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/23 9:34
 * @Version: v1.0
 * @Modified：
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {
    @Resource
    private StudentRepository studentRepository;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void saveStudentClass() {
        studentRepository.findAllByIsValidatedEqualsOrderByClassId(Dic.TAKE_EFFECT_OPEN)
                .parallelStream()
                .peek(s -> {
                    String key = "classRoom#".concat(s.getClassId());
                    stringRedisTemplate.opsForSet().add(key, s.getId());
                }).collect(Collectors.groupingBy(StudentEntitys::getClassId))
                .entrySet()
                .forEach(m -> {
                    String key = "classRoom#".concat(m.getKey());
                    stringRedisTemplate.expire(key, 7, TimeUnit.DAYS);
                });
    }

}
