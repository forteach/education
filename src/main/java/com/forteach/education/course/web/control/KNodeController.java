package com.forteach.education.course.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.domain.KNode;
import com.forteach.education.course.service.KNodeService;
import com.forteach.education.course.web.req.KNodeAll;
import com.forteach.education.util.UpdateUtil;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-6 08:59
 * @Version: 1.0
 * @Description: 知识点　控制层
 */
@RestController
@Api(value = "知识点操作", description = "操作知识点", tags = {"操作知识点"})
@RequestMapping(name = "kNode", path = "/kNode", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class KNodeController {

    @Resource
    private KNodeService kNodeService;

    @UserLoginToken
    @ApiOperation(value = "保存知识点")
    @PostMapping("/save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeName", value = "知识点名称", example = "电子商务定义", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "nodeDesc", value = "知识点描述", example = "知识点说明", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "courseId", value = "科目课程ID", example = "科目课程ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterId", value = "章节ID", dataType = "string", paramType = "from"),
    })
    public WebResult save(@ApiParam(name = "kNode", value = "知识点对象") @RequestBody KNodeAll req) {
        MyAssert.blank(req.getNodeName(), DefineCode.ERR0010, "知识点名称不为空");
        MyAssert.blank(req.getCourseId(), DefineCode.ERR0010, "课程id不为空");
        KNode kn = new KNode();
        UpdateUtil.copyNullProperties(req, kn);
        kn.setCreateUser(req.getCreateUser());
        return WebResult.okResult(kNodeService.save(kn));
    }

    @ApiOperation(value = "根据章节ID查询知识点信息")
    @PostMapping("/findByChapter")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节ID", dataType = "string", required = true, readOnly = true)
    })
    public WebResult findByChapter(@ApiParam(name = "chapterId", value = "章节ID", required = true, readOnly = true) @RequestBody String chapterId) {
        MyAssert.blank(chapterId, DefineCode.ERR0010, "章节ID不为空");
        return WebResult.okResult(kNodeService.findByChapterId(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId"))));
    }

    @ApiOperation(value = "根据课程ID查询知识点信息")
    @PostMapping("/findByCourse")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程ID", dataType = "string", required = true, readOnly = true)
    })
    public WebResult findByCourse(@ApiParam(name = "courseId", value = "课程ID", required = true, readOnly = true) @RequestBody String courseId) {
        MyAssert.blank(courseId, DefineCode.ERR0010, "课程ID不为空");
        return WebResult.okResult(kNodeService.findByCourseId(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }

    @ApiOperation(value = "根据知识点ID删除知识点(物理删除)")
    @PostMapping("/deleteByKNodeId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kNodeId", value = "知识点ID", example = "ff8081816782652001678265779b0000", dataType = "string", required = true)
    })
    public WebResult deleteByKNodeId(@ApiParam(name = "kNodeId", value = "知识点ID", required = true) @RequestBody String kNodeId) {
        MyAssert.blank(kNodeId, DefineCode.ERR0010, "知识点ID不为空");
        kNodeService.deleteById(String.valueOf(JSONObject.parseObject(kNodeId).getString("kNodeId")));
        return WebResult.okResult();
    }

    @UserLoginToken
    @ApiOperation(value = "改知识点为无效状态")
    @PostMapping("/deleteIsValidById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kNodeId", value = "知识点ID", example = "ff8081816782652001678265779b0000", dataType = "string", required = true)
    })
    public WebResult deleteIsValidById(@ApiParam(name = "kNodeId", value = "知识点ID", required = true) @RequestBody String kNodeId) {
        MyAssert.blank(kNodeId, DefineCode.ERR0010, "知识点ID不为空");
        kNodeService.deleteIsValidById(String.valueOf(JSONObject.parseObject(kNodeId).getString("kNodeId")));
        return WebResult.okResult();
    }
}
