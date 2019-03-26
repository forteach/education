package com.forteach.education.course.web.control;


import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.service.CoursewareService;
import com.forteach.education.course.web.req.CoursewareAll;
import com.forteach.education.course.web.req.FindImpCoursewareReq;
import com.forteach.education.course.web.req.ImpCoursewareAll;
import com.forteach.education.course.web.res.CourseAtlitListResp;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 16:52
 * @Version: 1.0
 * @Description:　科目课程重要课件信息
 */
@Slf4j
@RestController
@RequestMapping(path = "/courseware", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "课程科目操作", tags = {"课程科目重要课件信息"})
public class CoursewareController {

    @Resource
    private CoursewareService coursewareService;

    @ApiOperation(value = "保存课程科目信息", notes = "保存科目课程信息")
    @PostMapping("/savefile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "importantType", value = "1、教案 2、课件", dataTypeClass = String.class),
            @ApiImplicitParam(name = "datumType", value = "课件类型 1、文件 3、视频", dataTypeClass = String.class),
            @ApiImplicitParam(name = "files", value = "图集文件url", dataTypeClass = CoursewareAll.class)
    })
    public WebResult save(@ApiParam(name = "courseReq", value = "科目课程对象") @RequestBody ImpCoursewareAll req) {
        MyAssert.blank(req.getChapterId(), DefineCode.ERR0010, "章节编号不为空");
        MyAssert.blank(req.getImportantType(), DefineCode.ERR0010, "课件资料类型不为空");
        MyAssert.blank(req.getDatumType(), DefineCode.ERR0010, "课件类型不为空");
        ImpCoursewareAll resp = coursewareService.saveFile(req);
        return WebResult.okResult(resp);

    }

    @ApiOperation(value = "保存课件图集信息", notes = "保存课件图集信息")
    @PostMapping("/saveCourseAtlit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "photoDatumName", value = "图集名称", dataTypeClass = String.class),

            @ApiImplicitParam(name = "files", value = "图集文件url", dataTypeClass = CoursewareAll.class)
    })
    public WebResult saveCourseAtlit(@ApiParam(name = "courseReq", value = "科目课程对象") @RequestBody ImpCoursewareAll req) {
        MyAssert.blank(req.getChapterId(), DefineCode.ERR0010, "章节编号不为空");
        MyAssert.blank(req.getPhotoDatumName(), DefineCode.ERR0010, "图集名称不为空");
        MyAssert.elt(0, req.getFiles().size(), DefineCode.ERR0010, "图集文件url不为空");
        List<CoursewareAll> list = coursewareService.saveCourseAtlit(req);
        return WebResult.okResult(new CourseAtlitListResp(req.getChapterId(), list.size(), list));

    }

    @ApiOperation(value = "获得重要课件列表", notes = "获得重要课件列表(教案、课件)")
    @PostMapping("/getImpCourseware")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataTypeClass = Course.class, required = true),
            @ApiImplicitParam(name = "importantType", value = "重要课件资料类型1 教案 2 课件", dataTypeClass = String.class),
            @ApiImplicitParam(name = "datumType", value = "课件类型1、文件  3 视频", dataTypeClass = String.class)
    })
    public WebResult getImpCourseware(@ApiParam(name = "courseReq", value = "科目课程对象", required = true) @RequestBody FindImpCoursewareReq req) {
        MyAssert.blank(req.getChapterId(), DefineCode.ERR0010, "章节编号不为空");
        MyAssert.blank(req.getImportantType(), DefineCode.ERR0010, "课件资料类型不为空");
        MyAssert.blank(req.getDatumType(), DefineCode.ERR0010, "课件类型不为空");
        return WebResult.okResult(coursewareService.getImpCourseware(req.getChapterId(), req.getImportantType(), req.getDatumType()));
    }

    @ApiOperation(value = "获得重要课件图集列表", notes = "获得重要课件图集列表")
    @PostMapping("/getCourseArlitsList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataTypeClass = String.class, required = true),
    })
    public WebResult getCourseArlitsList(@ApiParam(name = "chapterId", value = "章节编号", required = true) @RequestBody String chapterId) {
        MyAssert.blank(chapterId, DefineCode.ERR0010, "章节编号不为空");
        List<CoursewareAll> list = coursewareService.getCourseArlitsList(JSONObject.parseObject(chapterId).getString("chapterId"));
        return WebResult.okResult(new CourseAtlitListResp(chapterId, list.size(), list));

    }

    @ApiOperation(value = "获得重要课件图集列表", notes = "获得重要课件图集列表")
    @PostMapping("/getPhotoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "arlitId", value = "图集编号", dataTypeClass = String.class, required = true),
    })
    public WebResult getPhotoList(@ApiParam(name = "arlitId", value = "图集编号", required = true) @RequestBody String arlitId) {
        MyAssert.blank(arlitId, DefineCode.ERR0010, "图集编号不为空");
        return WebResult.okResult(coursewareService.getPhotoList(JSONObject.parseObject(arlitId).getString("arlitId")));
    }

}
