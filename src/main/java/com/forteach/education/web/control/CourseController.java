package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.Course;
import com.forteach.education.service.CourseService;
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

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 16:52
 * @Version: 1.0
 * @Description:　科目信息操作
 */
@RestController
@RequestMapping("/course")
@Api(value = "科目操作", tags = {"科目操作相关信息"})
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "保存科目信息", notes = "保存文件资源文件信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(name = "course", value = "科目对象", required = true) @RequestBody Course course){
        return WebResult.okResult(courseService.save(course));
    }

    @ApiOperation(value = "修改科目信息", notes = "修改科目信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(name = "course", value = "科目对象", required = true) @RequestBody Course course){
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
}
