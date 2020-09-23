package com.forteach.education.course.repository;

import com.forteach.education.course.domain.Course;
import com.forteach.education.course.domain.TeacherClassCourse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-20 13:51
 * @version: 1.0
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

    @Resource
    private CourseRepository courseRepository;
    @Resource
    private CourseEntrityRepository courseEntrityRepository;
    @Resource
    private TeacherClassCourseRepository teacherClassCourseRepository;

    @Test
    public void saveCourse() {
        List<TeacherClassCourse> list = teacherClassCourseRepository.findAll();
        courseEntrityRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .filter(o -> o.getCourseId() != null && o.getCourseName() != null)
                .forEach(o -> {
                    list.forEach(t -> {
                        if (o.getCourseId().equals(t.getCourseId())) {
                            Course course = new Course();
                            course.setCourseId(o.getCourseId());
                            course.setCourseNumber(o.getCourseId());
                            course.setCourseName(o.getCourseName());
                            course.setCreateUser(t.getTeacherId());
                            courseRepository.save(course);
                        }
                    });
                });
    }
}