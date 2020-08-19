package com.forteach.education.count.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.count.service.CourseCountService;
import com.forteach.education.count.web.req.CourseCountReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 10:10
 * @version: 1.0
 * @description:
 */
@RestController
@Api(value = "课程统计信息接口", tags = {"统计各个课程相关的信息接口"})
@RequestMapping(path = "/courseCount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CourseCountController {

    private final TokenService tokenService;

    private final CourseCountService courseCountService;

    @Autowired
    public CourseCountController(TokenService tokenService, CourseCountService courseCountService) {
        this.tokenService = tokenService;
        this.courseCountService = courseCountService;
    }

    @UserLoginToken
    @ApiOperation(value = "查询课程章节对应的统计信息")
    @PostMapping(path = "/findCourseCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "chapterId", value = "章节", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "classId", value = "班级", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "circleId", value = "课程id", example = "0043fb829d984207ac6d6766df7ca2c5", dataType = "string", paramType = "query")
    })
    public WebResult findCourseCount(@RequestBody CourseCountReq courseCountReq, HttpServletRequest httpServletRequest){
        MyAssert.isNull(courseCountReq.getCourseId(), DefineCode.ERR0010, "科目编号不为空");
        MyAssert.isNull(courseCountReq.getChapterId(), DefineCode.ERR0010, "章节编号不为空");
        MyAssert.isNull(courseCountReq.getClassId(), DefineCode.ERR0010, "班级编号不为空");
        courseCountReq.setTeacherId(tokenService.getTeacherId(httpServletRequest));
        return WebResult.okResult(courseCountService.findCourseCount(courseCountReq));
    }


    @UserLoginToken
    @ApiOperation(value = "查询加入课堂的学生信息")
    @GetMapping("/findJoinCircleStudent")
    @ApiImplicitParam(name = "circleId", value = "课堂id", required = true, dataType = "string", paramType = "query")
    public WebResult findJoinCircleStudents(@RequestBody String circleId){
        MyAssert.isNull(circleId, DefineCode.ERR0010, "课堂id不为空");
        return WebResult.okResult(courseCountService.findJoinCircleStudent(JSONObject.parseObject(circleId).getString("circleId")));
    }
}
