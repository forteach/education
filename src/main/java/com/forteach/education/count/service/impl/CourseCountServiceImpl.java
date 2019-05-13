package com.forteach.education.count.service.impl;

import com.forteach.education.count.dto.ICourseCount;
import com.forteach.education.count.repository.CourseDrillCountRepository;
import com.forteach.education.count.repository.CoursePrepareCountRepository;
import com.forteach.education.count.repository.CourseTaskCountRepository;
import com.forteach.education.count.service.CourseCountService;
import com.forteach.education.count.web.req.CourseCountReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 15:08
 * @version: 1.0
 * @description: 查询统计信息
 */
@Service
public class CourseCountServiceImpl implements CourseCountService{

    private final CoursePrepareCountRepository coursePrepareCountRepository;
    private final CourseDrillCountRepository courseDrillCountRepository;
    private final CourseTaskCountRepository courseTaskCountRepository;

    @Autowired
    public CourseCountServiceImpl(CoursePrepareCountRepository coursePrepareCountRepository,
                                  CourseDrillCountRepository courseDrillCountRepository,
                                  CourseTaskCountRepository courseTaskCountRepository) {
        this.coursePrepareCountRepository = coursePrepareCountRepository;
        this.courseDrillCountRepository = courseDrillCountRepository;
        this.courseTaskCountRepository = courseTaskCountRepository;
    }

    @Override
    public List<ICourseCount> findCourseCount(CourseCountReq courseCountReq) {
        return coursePrepareCountRepository.findCourseCount(
                courseCountReq.getCourseId(),
                courseCountReq.getChapterId(),
                courseCountReq.getClassId(),
                courseCountReq.getCircleId());
    }

}
