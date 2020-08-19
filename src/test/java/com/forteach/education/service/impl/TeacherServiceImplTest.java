package com.forteach.education.service.impl;

import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.classes.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-29 16:40
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceImplTest {

    @Resource
    private TeacherService teacherService;

    @Test
    public void save() {
        Teacher teacher = teacherService.save(
                Teacher.builder()
                        .specialtyId("2c9180926746230801674625e56f0001")
                        .teacherCode("T0001")
                        .teacherName("李si")
                        .build()
        );
        log.info("teacher : {} ", teacher.toString());
    }

    @Test
    public void findTeachersBySpecialtyId() {
        List<Teacher> list = teacherService.findTeachersBySpecialtyId("");
        list.forEach(teacher -> {
            log.info("teacher : {}", teacher.toString());
        });
    }
}