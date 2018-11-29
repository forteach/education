package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.Course;
import com.forteach.education.domain.ViewDatum;
import com.forteach.education.service.CourseService;
import com.forteach.education.service.FileDatumService;
import com.forteach.education.service.ViewDatumService;
import com.forteach.education.web.req.CourseReq;
import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/course")
@Api(value = "课程科目操作", tags = {"课程科目操作相关信息"})
public class CourseController {

    private final CourseService courseService;
    private final FileDatumService fileDatumService;
    private final ViewDatumService viewDatumService;

    @Autowired
    public CourseController(CourseService courseService, FileDatumService fileDatumService, ViewDatumService viewDatumService) {
        this.courseService = courseService;
        this.fileDatumService = fileDatumService;
        this.viewDatumService = viewDatumService;
    }

    @ApiOperation(value = "保存课程科目信息", notes = "保存科目课程信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(name = "course", value = "科目课程对象", required = true) @RequestBody CourseReq courseReq){
        return WebResult.okResult(courseService.save(courseReq));
    }

    @ApiOperation(value = "修改科目课程信息", notes = "修改科目信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(name = "course", value = "科目课程对象", required = true) @RequestBody Course course){
        return WebResult.okResult(courseService.edit(course));
    }

    @ApiOperation(value = "删除科目信息", notes = "删除科目对象 (物理删除)")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(name = "course", value = "科目对象", required = true) @RequestBody Course course){
        courseService.delete(course);
        return WebResult.okResult();
    }

    @ApiOperation(value = "删除文件信息", notes = "根据文件资源ID 删除科目信息")
    @PostMapping("/deleteById")
    public WebResult deleteById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据资源ID 删除对应科目信息", type = "string", required = true) @RequestBody String courseId){
        courseService.deleteById(String.valueOf(JSONObject.parseObject(courseId).get("courseId")));
        return WebResult.okResult();
    }

    @PostMapping("/getCourseId")
    @ApiOperation(value = "获取文件信息", notes = "根据文件资源ID查询科目信息")
    public WebResult getCourseByCourseId(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据资源ID 查询对应科目信息", type = "string", required = true) @RequestBody String courseId){
        return WebResult.okResult(courseService.getCourseById(String.valueOf(JSONObject.parseObject(courseId).get("courseId"))));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询分页科目信息")
    @PostMapping("/findAll")
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
    public WebResult deleteIsValidById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据资源ID 逻辑删除对应科目信息", type = "string", required = true) @RequestBody String courseId){
        courseService.deleteIsValidById(courseId);
        return WebResult.okResult();
    }

    @ApiOperation(value = "根据课程id查询文件信息", notes = "根据科目课程ID查询文件资料信息")
    @PostMapping("/findFileDatumByCourseId")
    public WebResult findFileDatumByCourseId(@Valid @NotBlank(message = "科目课程ID不为空") @ApiParam(name = "courseId", value = "根据章节 ID 查询文件信息", required = true) @RequestBody String courseId){
        return WebResult.okResult(fileDatumService.findFileDatumByCourseId(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }

    @ApiOperation(value = "保存课程宣传片", notes = "保存课程的宣传片视频信息")
    @PostMapping("/saveViewDatum")
    public WebResult saveViewDatum(@Valid @NotNull(message = "视频信息不为空") @ApiParam() @RequestBody ViewDatum viewDatum){
        return WebResult.okResult(viewDatumService.save(viewDatum));
    }

    @ApiOperation(value = "根据课程ID查询宣传片信息", notes = "根据课程ID查询宣传片信息")
    @PostMapping("/findViewDatumByCourseId")
    public WebResult findViewDatumByCourseId(@Valid @NotBlank(message = "科目课程ID不为空") @ApiParam(name = "courseId", value = "根据课程 ID 查询文件信息", required = true) @RequestBody String courseId){
        return WebResult.okResult(viewDatumService.findViewDatumByCourseId(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }


}
