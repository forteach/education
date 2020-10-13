package com.forteach.education.course.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.service.CourseChapterReviewService;
import com.forteach.education.course.web.req.CourseChapterReviewSaveReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 11:16
 * @version: 1.0
 * @description:
 */
@RestController
@Api(value = "课程章节评论操作", tags = {"对课程章节评价进行操作"})
@RequestMapping(path = "/courseChapterReview", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CourseChapterReviewController {

    private final CourseChapterReviewService courseChapterReviewService;

    private final TokenService tokenService;

    @Autowired
    public CourseChapterReviewController(CourseChapterReviewService courseChapterReviewService, TokenService tokenService) {
        this.courseChapterReviewService = courseChapterReviewService;
        this.tokenService = tokenService;
    }

    @ApiOperation(value = "保存学生评价的章节信息", notes = "学生只能评价一次")
    @UserLoginToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节id", dataType = "string", required = true, paramType = "form"),
            @ApiImplicitParam(name = "score", value = "评分", dataType = "int", required = true, paramType = "form")
    })
    @PostMapping("/save")
    public WebResult save(@RequestBody CourseChapterReviewSaveReq reviewSaveReq, HttpServletRequest request) {
        MyAssert.isNull(reviewSaveReq.getChapterId(), DefineCode.ERR0010, "章节id不为空");
        MyAssert.isNull(reviewSaveReq.getScore(), DefineCode.ERR0010, "评分不为空");
        reviewSaveReq.setStudentId(tokenService.getStudentId(request));
        return courseChapterReviewService.save(reviewSaveReq);
    }

    @ApiOperation(value = "查询当课程章节评价的信息", notes = "查询章节评价信息(评价的分数, 评价的人数)")
    @PostMapping("/findChapterReview")
    @ApiImplicitParam(name = "chapterId", value = "课程章节id", dataType = "string", required = true, paramType = "query")
    public WebResult findChapterReview(@RequestBody String chapterId) {
        MyAssert.isNull(chapterId, DefineCode.ERR0010, "课程章节不为空");
        return WebResult.okResult(courseChapterReviewService.findChapterReview(JSONObject.parseObject(chapterId).getString("chapterId")));
    }

    @ApiOperation(value = "查询评价过的所有学生", notes = "查询所有评论过的学生列表信息")
    @PostMapping("/findCourseChapterStudentsAll")
    @ApiImplicitParam(name = "chapterId", value = "课程章节id", dataType = "string", required = true, paramType = "query")
    public WebResult findCourseChapterStudentsAll(@RequestBody String chapterId) {
        MyAssert.isNull(chapterId, DefineCode.ERR0010, "课程章节不为空");
        return WebResult.okResult(courseChapterReviewService.findCourseChapterStudentsAll(JSONObject.parseObject(chapterId).getString("chapterId")));
    }

    @UserLoginToken
    @ApiOperation(value = "查询我的评价", notes = "学生端查询自己评价当前课程章节信息")
    @PostMapping("/myCourseChapterReview")
    public WebResult findMyCourseChapterReview(@RequestBody String chapterId, HttpServletRequest request) {
        MyAssert.isNull(chapterId, DefineCode.ERR0010, "课程章节不为空");
        String chapterIdStr = JSONObject.parseObject(chapterId).getString("chapterId");
        String studentId = tokenService.getStudentId(request);
        return WebResult.okResult(courseChapterReviewService.findMyCourseChapterReview(studentId, chapterIdStr));
    }
}
