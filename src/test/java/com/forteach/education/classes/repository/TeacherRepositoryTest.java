package com.forteach.education.classes.repository;

import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.util.SortUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-6 14:27
 * @version: 1.0
 * @description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherRepositoryTest {

    @Resource
    private TeacherRepository teacherRepository;

    @Test
    public void findByIsValidatedEquals(){

        Page<Teacher> teachers = teacherRepository.findByIsValidatedEqualsOrderByCreateTimeDesc(TAKE_EFFECT_OPEN, PageRequest.of(1, 10, SortUtil.getSort(new SortVo())));
        teachers.get().forEach(System.out::println);
    }

}