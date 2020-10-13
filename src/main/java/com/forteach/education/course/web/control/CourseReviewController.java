package com.forteach.education.course.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.domain.CourseReviewDescribe;
import com.forteach.education.course.service.CourseReviewService;
import com.forteach.education.course.web.req.CourseReviewReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-9 09:59
 * @version: 1.0
 * @description: 课程评论相关
 */
@RestController
@Api(value = "课程评论相关的接口", tags = {"课程评论相关接口"})
@RequestMapping(path = "/courseReview", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CourseReviewController {

    private final TokenService tokenService;
    private final CourseReviewService courseReviewService;

    private CourseReviewController(TokenService tokenService, CourseReviewService courseReviewService) {
        this.tokenService = tokenService;
        this.courseReviewService = courseReviewService;
    }

    @UserLoginToken
    @ApiOperation(value = "提交评论和老师回复", notes = "学生评论课程和老师回复的接口")
    @PostMapping("/save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true, dataType = "string", paramType = "form", example = "课程id不为空"),
            @ApiImplicitParam(name = "reviewId", value = "评论id", dataType = "string", paramType = "form", example = "学生新评论不用传, 教师回复必须传值"),
            @ApiImplicitParam(name = "reviewDescribe", value = "评论内容", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "score", value = "评论分数", dataType = "int", paramType = "form", example = "5"),
            @ApiImplicitParam(name = "reply", value = "老师回复内容", dataType = "string", paramType = "form", example = "当教师回复调用不能为空")

    })
    public WebResult save(@RequestBody CourseReviewDescribe courseReviewDescribe, HttpServletRequest request) {
        MyAssert.isNull(courseReviewDescribe.getCourseId(), DefineCode.ERR0010, "课程id不为空");
        courseReviewDescribe.setStudentId(tokenService.getStudentId(request));
        courseReviewDescribe.setTeacherId(tokenService.getTeacherId(request));
        return WebResult.okResult(courseReviewService.save(courseReviewDescribe));
    }

    @UserLoginToken
    @ApiOperation(value = "删除评论", notes = "逻辑删除课程评论信息")
    @PostMapping("/delete")
    @ApiImplicitParam(name = "reviewId", value = "评论id", dataType = "string", paramType = "from", required = true)
    public WebResult delete(@RequestBody String reviewId) {
        MyAssert.isNull(reviewId, DefineCode.ERR0010, "评论id不为空");
        courseReviewService.deleteReview(JSONObject.parseObject(reviewId).getString("reviewId"));
        return WebResult.okResult();
    }

    @UserLoginToken
    @GetMapping("/findReviewFirst")
    @ApiOperation(value = "查询课程评论", notes = "查询最近一条课程评论和评分")
    @ApiImplicitParam(name = "courseId", value = "课程id", dataType = "string", required = true, paramType = "from")
    public WebResult findReviewCourse(@RequestBody String courseId) {
        MyAssert.isNull(courseId, DefineCode.ERR0010, "课程id 不能为空");
        return WebResult.okResult(courseReviewService.findFirstReview(JSONObject.parseObject(courseId).getString("courseId")));
    }

    @PostMapping("/findReview")
    @ApiOperation(value = "分页查询课程评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "sortVo", value = "分页字段", dataTypeClass = SortVo.class, required = true, paramType = "query")
    })
    public WebResult findReviewPage(@RequestBody CourseReviewReq reviewReq) {
        MyAssert.isNull(reviewReq.getCourseId(), DefineCode.ERR0010, "课程id 不能为空");
        MyAssert.blank(String.valueOf(reviewReq.getSortVo().getPage()), DefineCode.ERR0010, "当前页码不为空");
        MyAssert.blank(String.valueOf(reviewReq.getSortVo().getSize()), DefineCode.ERR0010, "每页数量不为空");
        return WebResult.okResult(courseReviewService.findReviewListPage(reviewReq));
    }
}
