package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.CourseChapter;
import com.forteach.education.service.CourseChapterService;
import com.forteach.education.service.FileDatumService;
import com.forteach.education.web.req.CourseDataDatumReq;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:52
 * @Version: 1.0
 * @Description: 科目章节
 */
@RestController
@RequestMapping(path = "/courseChapter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "科目章节接口", tags = {"科目章节信息"})
public class CourseChapterController {

    private final CourseChapterService courseChapterService;
    private final FileDatumService fileDatumService;


    @Autowired
    public CourseChapterController(CourseChapterService courseChapterService, FileDatumService fileDatumService) {
        this.courseChapterService = courseChapterService;
        this.fileDatumService = fileDatumService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存科目章节", notes = "保存科目章节信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", required = true, paramType = "from"),
            @ApiImplicitParam(name = "chapterName", value = "章节名称", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterParentId", value = "章节父编号", paramType = "from", dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "层级位置", defaultValue = "1", required = true, paramType = "from", dataType = "int"),
            @ApiImplicitParam(name = "chapterType", value = "目录类型", dataType = "int", required = true, paramType = "from"),
            @ApiImplicitParam(name = "chapterLevel", value = "章节树层级", dataType = "int", required = true, paramType = "from")
    })
    public WebResult save(@Valid @ApiParam(name = "courseChapter", value = "科目章节对象", required = true) @RequestBody CourseChapter courseChapter){
        return WebResult.okResult(courseChapterService.save(courseChapter));
    }

    @ApiOperation(value = "修改科目章节", notes = "修改科目章节信息")
    @PostMapping("/edit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterName", value = "章节名称", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterParentId", value = "章节父编号", paramType = "from", dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "层级位置", defaultValue = "1", paramType = "from", dataType = "int"),
            @ApiImplicitParam(name = "chapterType", value = "目录类型", dataType = "int", paramType = "from"),
            @ApiImplicitParam(name = "chapterLevel", value = "章节树层级", dataType = "int", paramType = "from")
    })
    public WebResult edit(@Valid @ApiParam(name = "courseChapter", value = "修改科目章节信息", required = true) @RequestBody CourseChapter courseChapter) {
        return WebResult.okResult(courseChapterService.edit(courseChapter));
    }

    @ApiOperation(value = "查询科目章节信息", notes = "根据章节ID 查询对应的信息")
    @PostMapping("/getCourseChapterById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "科目id", required = true, dataType = "string")
    })
    public WebResult getCourseChapterById(@Valid @NotEmpty(message = "科目ID不为空") @ApiParam(value = "根据科目ID 查询对应上层科目信息", name = "chapterId", required = true) @RequestBody String chapterId){
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "科目id", required = true, dataType = "string")
    })
    public WebResult deleteById(@Valid @NotEmpty(message = "章节ID不为空") @ApiParam(name = "chapterId", value = "根据章节ID删除对应的信息(物理删除)", required = true) String chapterId){
        courseChapterService.deleteIsValidById(chapterId);
        return WebResult.okResult();
    }

    /**
     * 根据科目ID 查询章节信息
     * @param courseId
     * @return
     */
    @PostMapping("/findByCourseId")
    @ApiOperation(value = "查找章节信息", notes = "客户端根据科目ID查询章节目录树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "章节id", required = true, dataType = "string")
    })
    public WebResult findByCourseId(@Valid @NotEmpty(message = "科目ID不为空") @ApiParam(name = "courseId", value = "根据章节ID 查询对应上层科目信息", required = true) @RequestBody String courseId){
        return WebResult.okResult(courseChapterService.findByCourseId(JSONObject.parseObject(courseId).getString("courseId")));
    }

//    @PostMapping("/findByChapterParentId")
//    @ApiOperation(value = "根据父章节ID查询对应子小节信息", notes = "根据父章节ID查询对应子小节id和名称")
//    public WebResult findByChapterParentId(
//            @Valid @NotBlank(message = "父章节ID不为空") @ApiParam(value = "父章节ID", name = "chapterParentId", type = "string", required = true) @RequestBody String chapterParentId){
//        return WebResult.okResult(courseChapterService.findByChapterParentId(JSONObject.parseObject(chapterParentId).getString("chapterParentId")));
//    }

//    @PostMapping("/findAllCourseChapter")
//    @ApiOperation(value = "查找章节信息", notes = "管理端查询最上层章节")
//    public WebResult findAllCourseChapter(@Valid @ApiParam(name = "courseChapterVo", value = "管理端根据科目ID 查询对应上层科目信息", required = true) @RequestBody CourseChapterVo vo){
//        return WebResult.okResult(courseChapterService.findAllCourseChapter(vo));
//    }

    /**
     * 批量保存科目章节资料信息
     * @param courseDataDatumReq
     * @return
     */
    @PostMapping("/saveFilesDatum")
    @ApiOperation(value = "批量保存资料信息")
    public WebResult saveFilesDatum(@Valid @ApiParam(value = "保存章节资料文件信息") @RequestBody CourseDataDatumReq courseDataDatumReq){
        fileDatumService.saveCourseDataDatum(courseDataDatumReq);
        return WebResult.okResult();
    }
}
