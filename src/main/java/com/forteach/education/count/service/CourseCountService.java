package com.forteach.education.count.service;

import com.forteach.education.count.dto.ICourseCount;
import com.forteach.education.count.web.req.CourseCountReq;

import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 15:06
 * @version: 1.0
 * @description:
 */
public interface CourseCountService {

    /**
     * 查询课程统计信息
     */
    List<ICourseCount> findCourseCount(CourseCountReq courseCountReq);

}
