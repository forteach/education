package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.CourseChapter;
import com.forteach.education.service.CourseChapterService;
import com.forteach.education.web.vo.CourseChapterVo;
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
 * @Date: 18-11-21 15:52
 * @Version: 1.0
 * @Description: 科目章节
 */
@RestController
@RequestMapping("/courseChapter")
@Api(value = "科目章节接口", tags = {"科目章节信息"})
public class CourseChapterController {

    @Autowired
    private CourseChapterService courseChapterService;

    @PostMapping("/save")
    @ApiOperation(value = "保存科目章节", notes = "保存科目章节信息")
    public WebResult save(@Valid @ApiParam(name = "courseChapter", value = "科目章节对象", required = true) @RequestBody CourseChapter courseChapter){
        return WebResult.okResult(courseChapterService.save(courseChapter));
    }

    @ApiOperation(value = "修改科目章节", notes = "修改科目章节信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(name = "courseChapter", value = "修改科目章节信息", required = true) @RequestBody CourseChapter courseChapter) {
        return WebResult.okResult(courseChapterService.edit(courseChapter));
    }

    @ApiOperation(value = "查询科目章节信息", notes = "根据章节ID 查询对应的信息")
    @PostMapping("/getCourseChapterById")
    public WebResult getCourseChapterById(@Valid @NotBlank(message = "科目ID不为空") @ApiParam(value = "根据资源ID 查询对应上层科目信息", name = "chapterId", required = true) @RequestBody String chapterId){
        return WebResult.okResult(courseChapterService.getCourseChapterById(JSONObject.parseObject(chapterId).getString("chapterId")));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除科目章节信息", notes = "删除科目章节信息(物理删除)")
    public WebResult delete(@Valid @ApiParam(value = "删除科目章节信息(物理删除)", name = "courseChapter", required = true) @RequestBody CourseChapter courseChapter){
        courseChapterService.delete(courseChapter);
        return WebResult.okResult();
    }

    @PostMapping("/deleteById")
    @ApiOperation(notes = "根据ID删除对应的科目章节信息", value = "删除科目章节信息")
    public WebResult deleteById(@Valid @NotBlank(message = "章节ID不为空") @ApiParam(name = "chapterId", value = "根据章节ID删除对应的信息(物理删除)", required = true) String chapterId){
        courseChapterService.deleteIsValidById(chapterId);
        return WebResult.okResult();
    }

    /**
     * 根据科目ID 查询第一层的章节信息
     * @param courseId
     * @return
     */
    @PostMapping("/findByCourseId")
    @ApiOperation(value = "查找章节信息", notes = "客户端根据科目ID查询第一层的章节")
    public WebResult findByCourseId(@Valid @NotNull(message = "科目ID不为空") @ApiParam(name = "courseId", value = "根据章节ID 查询对应上层科目信息", required = true) @RequestBody String courseId){
        return WebResult.okResult(courseChapterService.findByCourseId(JSONObject.parseObject(courseId).getString("courseId")));
    }

    @PostMapping("/findByChapterParentId")
    @ApiOperation(value = "根据父章节ID查询对应子小节信息", notes = "根据父章节ID查询对应子小节id和名称")
    public WebResult findByChapterParentId(
            @Valid @NotBlank(message = "父章节ID不为空") @ApiParam(value = "父章节ID", name = "chapterParentId", type = "string", required = true) @RequestBody String chapterParentId){
        return WebResult.okResult(courseChapterService.findByChapterParentId(JSONObject.parseObject(chapterParentId).getString("chapterParentId")));
    }

    @PostMapping("/findAllCourseChapter")
    @ApiOperation(value = "查找章节信息", notes = "管理端查询最上层章节")
    public WebResult findAllCourseChapter(@Valid @ApiParam(name = "courseChapterVo", value = "管理端根据科目ID 查询对应上层科目信息", required = true) @RequestBody CourseChapterVo vo){
        return WebResult.okResult(courseChapterService.findAllCourseChapter(vo));
    }
}
