package com.forteach.education.count.service.impl;

import com.forteach.education.authority.domain.StudentEntitys;
import com.forteach.education.common.service.StudentService;
import com.forteach.education.count.domain.CourseJoinChapter;
import com.forteach.education.count.dto.ICourseCount;
import com.forteach.education.count.repository.*;
import com.forteach.education.count.service.CourseCountService;
import com.forteach.education.count.web.req.CourseCountReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;
/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 15:08
 * @version: 1.0
 * @description: 查询统计信息
 */
@Service
public class CourseCountServiceImpl implements CourseCountService{

    private final StudentService studentService;
    private final CourseChapterCountRepository courseChapterCountRepository;

    @Autowired
    public CourseCountServiceImpl(StudentService studentService,
                                  CourseChapterCountRepository courseChapterCountRepository,) {
        this.studentService = studentService;
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
    public List<StudentEntitys> findJoinCircleStudent(String circleId){
        CourseJoinChapter courseJoinChapter = courseJoinChapterRepository.findByIsValidatedEqualsAndCircleId(TAKE_EFFECT_OPEN, circleId);
        if (courseJoinChapter != null){
            return studentService.getStudentListByStr(courseJoinChapter.getStudents());
        }
        return null;
    }
}
