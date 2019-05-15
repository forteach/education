package com.forteach.education.count.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.forteach.education.authority.domain.StudentEntitys;
import com.forteach.education.common.service.StudentService;
import com.forteach.education.count.dto.ICourseCount;
import com.forteach.education.count.repository.CourseChapterCountRepository;
import com.forteach.education.count.repository.description.CourseJoinChapterDescriptionRepository;
import com.forteach.education.count.service.CourseCountService;
import com.forteach.education.count.web.req.CourseCountReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 15:08
 * @version: 1.0
 * @description: 查询统计信息
 */
@Service
public class CourseCountServiceImpl implements CourseCountService {

    private final StudentService studentService;
    private final CourseChapterCountRepository courseChapterCountRepository;
    private final CourseJoinChapterDescriptionRepository courseJoinChapterDescriptionRepository;

    @Autowired
    public CourseCountServiceImpl(StudentService studentService,
                                  CourseJoinChapterDescriptionRepository courseJoinChapterDescriptionRepository,
                                  CourseChapterCountRepository courseChapterCountRepository) {
        this.studentService = studentService;
        this.courseJoinChapterDescriptionRepository = courseJoinChapterDescriptionRepository;
        this.courseChapterCountRepository = courseChapterCountRepository;
    }

    @Override
    public List<ICourseCount> findCourseCount(CourseCountReq courseCountReq) {
        return courseChapterCountRepository.findCourseCount(
                courseCountReq.getCourseId(),
                courseCountReq.getChapterId(),
                courseCountReq.getClassId(),
                courseCountReq.getCircleId()
        );
    }

    @Override
    public List<StudentEntitys> findJoinCircleStudent(String circleId) {
        List<StudentEntitys> studentEntitys = CollUtil.newArrayList();
        courseJoinChapterDescriptionRepository.findByIsValidatedEqualsAndCircleId(TAKE_EFFECT_OPEN, circleId)
                .stream()
                .filter(Objects::nonNull)
                .forEach(s -> studentEntitys.add(studentService.getStudentEntitysById(s.getStudentId())));
        return studentEntitys;
    }
}
