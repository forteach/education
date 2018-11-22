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

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:52
 * @Version: 1.0
 * @Description: 科目章节
 */
@RestController
@RequestMapping("/courseChapter")
@Api(value = "科目章节接口", tags = {"操作科目章节信息"})
public class CourseChapterController {

    @Autowired
    private CourseChapterService courseChapterService;

    @PostMapping("/save")
    @ApiOperation(value = "保存科目章节", tags = "保存科目章节信息")
    public WebResult save(@Valid @ApiParam(value = "courseChapter", name = "科目章节对象", required = true) @RequestBody CourseChapter courseChapter){
        return WebResult.okResult(courseChapterService.save(courseChapter));
    }

    @ApiOperation(value = "修改科目章节", notes = "修改科目章节信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(value = "courseChapter", name = "修改科目章节信息", required = true) @RequestBody CourseChapter courseChapter) {
        return WebResult.okResult(courseChapterService.edit(courseChapter));
    }

    @ApiOperation(value = "查询科目章节信息", notes = "根据章节ID 查询对应的信息")
    @PostMapping("/getCourseChapterById")
    public WebResult getCourseChapterById(@Valid @ApiParam(value = "chapterId", name = "根据资源ID 查询对应上层科目信息", required = true) @RequestBody String chapterId){
        return WebResult.okResult(courseChapterService.getCourseChapterById(JSONObject.parseObject(chapterId).getString("chapterId")));
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除科目章节信息", notes = "删除科目章节信息(物理删除)")
    public WebResult delete(@Valid @ApiParam(name = "删除科目章节信息(物理删除)", value = "courseChapter", required = true) @RequestBody CourseChapter courseChapter){
        courseChapterService.delete(courseChapter);
        return WebResult.okResult();
    }

    @PostMapping("/deleteById")
    @ApiOperation(notes = "根据ID删除对应的科目章节信息", value = "删除科目章节信息")
    public WebResult deleteById(@Valid @ApiParam(value = "chapterId", name = "根据章节ID删除对应的信息(物理删除)", required = true) String chapterId){
        courseChapterService.deleteIsValidById(chapterId);
        return WebResult.okResult();
    }

    /**
     * 根据科目ID 查询第一层的章节信息
     * @param courseId
     * @return
     */
    @PostMapping("/findAllByCourseId")
    @ApiOperation(value = "查找章节信息", notes = "客户端根据科目ID查询第一层的章节")
    public WebResult findAllByCourseId(@Valid @ApiParam(value = "courseId", name = "根据资源ID 查询对应上层科目信息", required = true) @RequestBody String courseId){
        return WebResult.okResult(courseChapterService.findAllByCourseId(JSONObject.parseObject(courseId).getString("courseId")));
    }

    @PostMapping("/findAllCourseChapter")
    @ApiOperation(value = "查找章节信息", notes = "管理端查询最上层章节")
    public WebResult findAllCourseChapter(@Valid @ApiParam(value = "courseChapterVo", name = "根据资源ID 查询对应上层科目信息", required = true) @RequestBody CourseChapterVo vo){
        return WebResult.okResult(courseChapterService.findAllCourseChapter(vo));
    }
}
