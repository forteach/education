package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.Course;
import com.forteach.education.domain.Teacher;
import com.forteach.education.domain.ViewDatum;
import com.forteach.education.service.CourseService;
import com.forteach.education.service.CourseShareService;
import com.forteach.education.service.FileDatumService;
import com.forteach.education.service.ViewDatumService;
import com.forteach.education.web.req.CourseFileDataReq;
import com.forteach.education.web.req.CourseFileListReq;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.web.req.CourseReq;
import com.forteach.education.web.vo.DataDatumVo;
import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 16:52
 * @Version: 1.0
 * @Description:　科目课程信息操作
 */
@RestController
@RequestMapping(path = "/course", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "课程科目操作", tags = {"课程科目操作相关信息"})
public class CourseController {

    private final CourseService courseService;
    private final FileDatumService fileDatumService;
    private final ViewDatumService viewDatumService;
    private final CourseShareService courseShareService;

    @Autowired
    public CourseController(CourseService courseService, FileDatumService fileDatumService,
                            ViewDatumService viewDatumService, CourseShareService courseShareService) {
        this.courseService = courseService;
        this.fileDatumService = fileDatumService;
        this.viewDatumService = viewDatumService;
        this.courseShareService = courseShareService;
    }

    @ApiOperation(value = "保存课程科目信息", notes = "保存科目课程信息")
    @PostMapping("/save")
//    @JsonView(View.SummaryExtend.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "course", value = "科目课程对象", dataTypeClass = Course.class, required = true),
            @ApiImplicitParam(name = "teachers", value = "教师信息列表", dataTypeClass = Teacher.class)
    })
    public WebResult save(@Valid @ApiParam(name = "courseReq", value = "科目课程对象") @RequestBody CourseReq courseReq){
        return WebResult.okResult(courseService.save(courseReq));
    }

    @ApiOperation(value = "修改科目课程信息", notes = "修改科目信息")
    @PostMapping("/edit")
//    @JsonView(View.Summary.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "course", value = "科目课程对象", dataTypeClass = Course.class, required = true),
            @ApiImplicitParam(name = "teachers", value = "教师信息列表", dataTypeClass = Teacher.class)
    })
    public WebResult edit(@Valid @ApiParam(name = "courseReq", value = "科目课程对象", required = true) @RequestBody CourseReq courseReq){
        return WebResult.okResult(courseService.edit(courseReq));
    }

    @ApiOperation(value = "删除科目信息", notes = "删除科目对象 (物理删除)")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "course", value = "科目课程对象", dataTypeClass = Course.class, dataType = "string", required = true),
            @ApiImplicitParam(name = "teachers", value = "教师信息列表", dataTypeClass = Teacher.class)
    })
    public WebResult delete(@Valid @ApiParam(name = "course", value = "科目对象", required = true) @RequestBody Course course){
        courseService.delete(course);
        return WebResult.okResult();
    }

    @ApiOperation(value = "删除文件信息", notes = "根据文件资源ID 删除科目信息")
    @PostMapping("/deleteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true)
    })
    public WebResult deleteById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据科目ID 删除对应科目信息", type = "string", required = true) @RequestBody String courseId){
        courseService.deleteById(String.valueOf(JSONObject.parseObject(courseId).get("courseId")));
        return WebResult.okResult();
    }

    @PostMapping("/getCourseId")
//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "获取科目课程信息", notes = "根据科目课程ID查询科目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true, example = "{\"courseId\":\"2c918099676317d0016763e051f50000\"}")
    })
    public WebResult getCourseByCourseId(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据科目ID 查询对应科目信息", type = "string", required = true, example = "{\"courseId\":\"2c918099676317d0016763e051f50000\"}")
                                             @RequestBody String courseId){
        return WebResult.okResult(courseService.getCourseById(String.valueOf(JSONObject.parseObject(courseId).get("courseId"))));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询分页科目信息")
    @PostMapping("/findAll")
//    @JsonView(View.SummaryExtend.class)
    public WebResult findAll(@Valid @ApiParam(name = "sortVo", value = "分页查科目信息",required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(courseService.findAll(sortVo));
    }

    /**
     * 通过文件资源 ID 逻辑删除文件资源信息
     * @param courseId
     * @return
     */
    @ApiOperation(value = "使其无效", notes = "删除科目信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
//    @JsonView(View.SummaryExtend.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true)
    })
    public WebResult deleteIsValidById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据资源ID 逻辑删除对应科目信息", type = "string", required = true) @RequestBody String courseId){
        courseService.deleteIsValidById(courseId);
        return WebResult.okResult();
    }

    @ApiOperation(value = "根据课程id查询文件信息", notes = "根据科目课程ID查询文件资料信息")
    @PostMapping("/findFileDatumByCourseId")
//    @JsonView(View.SummaryExtend.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true),
            @ApiImplicitParam(name = "chapterId", value = "章节小节ID", dataType = "string"),
            @ApiImplicitParam(name = "mount", value = "是否挂载", dataType = "string", example = "Y/N"),
            @ApiImplicitParam(name = "sortVo", value = "分页对象", dataTypeClass = SortVo.class, required = true),
    })
    public WebResult findFileDatumByCourseId(@Valid @ApiParam(name = "courseId", value = "根据章节 ID 查询文件信息", required = true)
                                                 @RequestBody CourseFileDataReq courseFileDataReq){
        return WebResult.okResult(fileDatumService.findFileDatumByCourseId(courseFileDataReq));
    }

    @ApiOperation(value = "保存课程宣传片", notes = "保存课程的宣传片视频信息")
    @PostMapping("/saveViewDatum")
//    @JsonView(View.Summary.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "viewId", value = "视频编号", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "courseId", value = "科目课程ID", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "viewName", value = "视频名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "viewType", value = "视频类型", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "viewUrl", value = "视频URL", dataType = "string", paramType = "query")
    })
    public WebResult saveViewDatum(@Valid @NotNull(message = "视频信息不为空") @ApiParam() @RequestBody ViewDatum viewDatum){
        return WebResult.okResult(viewDatumService.save(viewDatum));
    }

    @ApiOperation(value = "根据课程ID查询宣传片信息", notes = "根据课程ID查询宣传片信息")
    @PostMapping("/findViewDatumByCourseId")
//    @JsonView(View.SummaryExtend.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true)
    })
    public WebResult findViewDatumByCourseId(@Valid @NotBlank(message = "科目课程ID不为空") @ApiParam(name = "courseId", value = "根据课程 ID 查询文件信息", required = true) @RequestBody String courseId){
        return WebResult.okResult(viewDatumService.findViewDatumByCourseId(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }

    /**
     * 保存课程轮播图信息
     * @param courseImagesReq
     * @return
     */
    @PostMapping("/saveCourseImages")
//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "保存课程科目轮播图", notes = "保存科目的轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true),
            @ApiImplicitParam(name = "images", value = "图册信息数组", dataTypeClass = DataDatumVo.class, required = true)
    })
    public WebResult saveCourseImages(@Valid @ApiParam(value = "课程ID和图片", name = "courseImagesReq") @RequestBody CourseImagesReq courseImagesReq){
        courseService.saveCourseImages(courseImagesReq);
        return WebResult.okResult();
    }

    /**
     * 查询轮播图信息
     * @param courseId
     * @return
     */
    @PostMapping("/findImagesByCourseId")
//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "查询课程轮播图", notes = "根据课程科目ID查询对应的轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true, example = "ff808181677d238701677d26fdae0002")
    })
    public WebResult findImagesByCourseId(@Valid @ApiParam(name = "courseId", value = "查询对应的", type = "string", required = true) @RequestBody String courseId){
        return WebResult.okResult(courseService.findImagesByCourseId(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }

    @PostMapping("/selectTeachersByCourseId")
    @ApiOperation(value = "根据课程ID查询对应的协作老师信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true, example = "ff808181677d238701677d26fdae0002")
    })
    public WebResult selectTeachersByCourseId(@Valid @ApiParam(name = "courseId", value = "查询对应的协作老师信息", type = "string", required = true) @RequestBody String courseId){
        return WebResult.okResult(courseShareService.selectCourseShareTeachersByCourseId(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }

    @PostMapping("/editCourseFileList")
    @ApiOperation(value = "修改选择的文件", notes = "参数是 fileDatums 名对象的数组")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "courseId", value = "科目课程ID", dataType = "string"),
            @ApiImplicitParam(name = "fileDatums", value = "文件列表对象列表", dataTypeClass = CourseFileListReq.class)
    })
    public WebResult editCourseFileList(@Valid @ApiParam(name = "fileDatums", value = "文件对象列表") @RequestBody CourseFileListReq courseFileListReq){
        fileDatumService.editCourseFileList(courseFileListReq);
        return WebResult.okResult();
    }

}
