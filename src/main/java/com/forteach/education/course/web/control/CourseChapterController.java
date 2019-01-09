package com.forteach.education.course.web.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.domain.CourseChapter;
import com.forteach.education.course.web.req.CourseChapterReq;
import com.forteach.education.course.service.CourseChapterService;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.course.web.req.CourseChapterEditReq;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:52
 * @Version: 1.0
 * @Description: 科目章节
 */
@Slf4j
@RestController
@RequestMapping(path = "/courseChapter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "科目章节接口", tags = {"科目章节信息"})
public class CourseChapterController {

    @Resource
    private  CourseChapterService courseChapterService;

    @PostMapping("/save")
    @ApiOperation(value = "保存科目章节", notes = "保存科目章节信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", required = true, paramType = "from"),
            @ApiImplicitParam(name = "chapterName", value = "章节名称", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterParentId", value = "章节父编号", paramType = "from", dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "层级位置", defaultValue = "1", required = true, paramType = "from", dataType = "int"),
            @ApiImplicitParam(name = "chapterType", value = "目录类型", dataType = "int", required = true, paramType = "from"),
            @ApiImplicitParam(name = "publish", value = "是否发布　Y(是) N(否)", dataType = "string", paramType = "from")
    })
    public WebResult save(@Valid @ApiParam(name = "courseChapter", value = "科目章节对象", required = true) @RequestBody CourseChapterReq req){
        CourseChapter cs=new  CourseChapter();
        UpdateUtil.copyNullProperties(req, cs);

        log.info("------*{}" , JSON.toJSONString(cs));

        WebResult r=WebResult.okResult(courseChapterService.save(cs));
        log.info("*********{}" ,JSON.toJSONString(r));
        return r;
    }

    @ApiOperation(value = "修改科目章节", notes = "修改科目章节信息")
    @PostMapping("/edit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目编号", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterName", value = "章节名称", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterParentId", value = "章节父编号", paramType = "from", dataType = "string"),
            @ApiImplicitParam(name = "sort", value = "层级位置", defaultValue = "1", paramType = "from", dataType = "int"),
            @ApiImplicitParam(name = "chapterType", value = "目录类型", dataType = "int", paramType = "from"),
            @ApiImplicitParam(name = "publish", value = "是否发布　Y(是) N(否)", dataType = "string", paramType = "from")
    })
    public WebResult edit(@Valid @ApiParam(name = "courseChapter", value = "修改科目章节信息", required = true) @RequestBody CourseChapterEditReq courseChapterEditReq) {
        return WebResult.okResult(courseChapterService.edit(courseChapterEditReq));
    }

    @ApiOperation(value = "查询科目章节信息", notes = "根据章节ID 查询对应的信息")
    @PostMapping("/getCourseChapterById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "科目id", required = true, dataType = "string")
    })
    public WebResult getCourseChapterById(@Valid @NotEmpty(message = "科目ID不为空") @ApiParam(value = "根据科目ID 查询对应上层科目信息", name = "chapterId", required = true) @RequestBody String chapterId){
        return WebResult.okResult(courseChapterService.getCourseChapterById(JSONObject.parseObject(chapterId).getString("chapterId")));
    }

//    @PostMapping("/delete")
//    @ApiOperation(value = "删除科目章节信息", notes = "删除科目章节信息(物理删除)")
//    public WebResult delete(@Valid @ApiParam(value = "删除科目章节信息(物理删除)", name = "courseChapter", required = true) @RequestBody CourseChapter courseChapter){
//        courseChapterService.delete(courseChapter);
//        return WebResult.okResult();
//    }

    @PostMapping("/deleteById")
    @ApiOperation(notes = "根据ID删除对应的科目章节信息(物理删除)", value = "删除科目章节信息(物理删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "科目id", required = true, dataType = "string")
    })
    public WebResult deleteById(@Valid @NotBlank(message = "章节不为空") @ApiParam(name = "chapterId", value = "根据章节ID删除对应的信息(物理删除)", required = true)@RequestBody String chapterId){
        courseChapterService.deleteById(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId")));
        return WebResult.okResult();
    }

    @PostMapping("/deleteIsValidById")
    @ApiOperation(notes = "根据ID删除对应的科目章节信息(逻辑删除)", value = "删除科目章节信息(逻辑删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "科目id", required = true, dataType = "string")
    })
    public WebResult deleteIsValidById(@Valid @NotBlank(message = "章节ID不为空") @ApiParam(name = "chapterId", value = "根据章节ID删除对应的信息(逻辑删除)", required = true)@RequestBody String chapterId){
        courseChapterService.deleteIsValidById(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId")));
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
//
//    /**
//     * 批量保存科目章节资料信息
//     * @param courseDataDatumReq
//     * @return
//     */
//    @PostMapping("/saveFilesDatum")
//    @ApiOperation(value = "批量保存资料信息")
//    public WebResult saveFilesDatum(@Valid @ApiParam(value = "保存章节资料文件信息") @RequestBody CourseDataDatumReq courseDataDatumReq){
//        fileDatumService.saveCourseDataDatum(courseDataDatumReq);
//        return WebResult.okResult();
//    }
}