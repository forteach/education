package com.forteach.education.course.web.control.verify;

import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.MyAssert;
import com.forteach.education.course.web.req.CourseSaveReq;
import org.springframework.stereotype.Component;

@Component
public class CourseVer {

    public static void saveValide(CourseSaveReq req) {

        MyAssert.isNull(req.getCourse(), DefineCode.ERR0010,"课程信息不能为空");

    }

}
