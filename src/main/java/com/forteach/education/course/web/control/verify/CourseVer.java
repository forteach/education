package com.forteach.education.course.web.control.verify;

import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.course.web.req.CourseSaveReq;
import org.springframework.stereotype.Component;

@Component
public class CourseVer {

    public static void saveValide(CourseSaveReq req) {
        MyAssert.isNull(req.getCourse(), DefineCode.ERR0010, "课程信息未填写");
        MyAssert.isNull(req.getCourse().getCourseName(), DefineCode.ERR0010, "课程名称不能为空");
        MyAssert.isNull(req.getCourse().getCourseNumber(), DefineCode.ERR0010, "课程编号不能为空");
    }
}
