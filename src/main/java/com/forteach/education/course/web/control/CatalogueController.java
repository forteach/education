package com.forteach.education.course.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.domain.Catalogue;
import com.forteach.education.course.service.CatalogueService;
import com.forteach.education.course.web.req.CourseChapterEditReq;
import com.forteach.education.course.web.req.CourseChapterReq;
import com.forteach.education.util.UpdateUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:52
 * @Version: 1.0
 * @Description: 科目章节
 */
@Slf4j
@RestController
@RequestMapping(path = "/catalogue", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "科目章节接口", tags = {"科目章节信息"})
public class CatalogueController {

    @Resource
    private CatalogueService catalogueService;

    @Resource
    private TokenService tokenService;

    @UserLoginToken
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
    public WebResult save(@ApiParam(name = "courseChapter", value = "科目章节对象", required = true) @RequestBody CourseChapterReq req, HttpServletRequest request) {
        req.setCreateUser(tokenService.getUserId(request));
        Catalogue cs = new Catalogue();
        UpdateUtil.copyNullProperties(req, cs);
        cs.setCreateUser(req.getCreateUser());
        return WebResult.okResult(catalogueService.save(cs));
    }

    @UserLoginToken
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
    public WebResult edit(@ApiParam(name = "courseChapter", value = "修改科目章节信息", required = true) @RequestBody CourseChapterEditReq courseChapterEditReq) {
        MyAssert.blank(courseChapterEditReq.getChapterId(), DefineCode.ERR0010, "章节编号不为空");
        return WebResult.okResult(catalogueService.edit(courseChapterEditReq));
    }

    @UserLoginToken
    @ApiOperation(value = "查询科目章节信息", notes = "根据章节ID 查询对应的信息")
    @PostMapping("/getCourseChapterById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "科目id", required = true, dataType = "string")
    })
    public WebResult getCourseChapterById(@ApiParam(value = "根据科目ID 查询对应上层科目信息", name = "chapterId", required = true) @RequestBody String chapterId) {
        MyAssert.blank(chapterId, DefineCode.ERR0010, "科目id不为空");
        return WebResult.okResult(catalogueService.getCourseChapterById(JSONObject.parseObject(chapterId).getString("chapterId")));
    }


//************************************************************************************************************************//

    @UserLoginToken
    @PostMapping("/deleteById")
    @ApiOperation(notes = "根据ID删除对应的科目章节信息(物理删除)", value = "删除科目章节信息(物理删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "科目id", required = true, dataType = "string")
    })
    public WebResult deleteById(@ApiParam(name = "chapterId", value = "根据章节ID删除对应的信息(物理删除)", required = true) @RequestBody String chapterId) {
        MyAssert.blank(chapterId, DefineCode.ERR0010, "科目id不为空");
        catalogueService.deleteById(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId")));
        return WebResult.okResult();
    }

    @UserLoginToken
    @PostMapping("/deleteIsValidById")
    @ApiOperation(notes = "根据ID删除对应的科目章节信息(逻辑删除)", value = "删除科目章节信息(逻辑删除)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "科目id", required = true, dataType = "string")
    })
    public WebResult deleteIsValidById(@ApiParam(name = "chapterId", value = "根据章节ID删除对应的信息(逻辑删除)", required = true) @RequestBody String chapterId) {
        MyAssert.blank(chapterId, DefineCode.ERR0010, "章节ID不为空");
        catalogueService.deleteIsValidById(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId")));
        return WebResult.okResult();
    }

    /**
     * 根据科目ID 查询章节信息
     *
     * @param courseId
     * @return
     */
    @UserLoginToken
    @PostMapping("/findByCourseId")
    @ApiOperation(value = "查找章节信息", notes = "客户端根据科目ID查询章节目录树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "章节id", required = true, dataType = "string")
    })
    public WebResult findByCourseId(@ApiParam(name = "courseId", value = "根据章节ID 查询对应上层科目信息", required = true) @RequestBody String courseId) {
        MyAssert.blank(courseId, DefineCode.ERR0010, "科目id不为空");
        return WebResult.okResult(catalogueService.findByCourseId(JSONObject.parseObject(courseId).getString("courseId")));
    }

}
