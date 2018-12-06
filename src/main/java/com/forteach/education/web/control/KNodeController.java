package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.KNode;
import com.forteach.education.service.KNodeService;
import com.forteach.education.web.req.KNodeReq;
import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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

    @ApiOperation(value = "保存知识点")
    @PostMapping("/save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nodeName", value = "知识点名称", example = "电子商务定义", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "nodeDesc", value = "知识点描述", example = "知识点说明", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "courseId", value = "科目课程ID", example = "科目课程ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterId", value = "章节ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "dataId", value = "资料数据ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "kNodeType", value = "知识点类型", dataType = "string", required = true, example = "1", paramType = "from")
    })
    public WebResult save(@Valid @RequestBody KNode kNode){
        return WebResult.okResult(kNodeService.save(kNode));
    }

    @ApiOperation(value = "修改知识点")
    @PostMapping("/edit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kNodeId", value = "知识点ID", dataType = "string", required = true, paramType = "from"),
            @ApiImplicitParam(name = "nodeName", value = "知识点名称", example = "电子商务定义", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "nodeDesc", value = "知识点描述", example = "知识点说明", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "courseId", value = "科目课程ID", example = "科目课程ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "chapterId", value = "章节ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "dataId", value = "资料数据ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "kNodeType", value = "知识点类型", dataType = "string", required = true, example = "1", paramType = "from")
    })
    public WebResult edit(@Valid @RequestBody KNode kNode){
        return WebResult.okResult(kNodeService.edit(kNode));
    }

    @ApiOperation(value = "分页筛选查询知识点信息")
    @PostMapping("/findAllPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目课程ID", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "chapterId", value = "章节ID", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "dataId", value = "资料id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "kNodeType", value = "知识点类型", example = "1", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "SortVo", value = "分页对象", dataTypeClass = SortVo.class, required = true, paramType = "query")
    })
    public WebResult findAllPage(@Valid @ModelAttribute @RequestBody KNodeReq kNodeReq){
        return WebResult.okResult(kNodeService.selectById(kNodeReq));
    }

    @ApiOperation(value = "根据知识点ID查询知识点信息")
    @PostMapping("/findByKNodeId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kNodeId", value = "知识点ID", example = "ff8081816782652001678265779b0000", dataType = "string", required = true, readOnly = true)
    })
    public WebResult findById(@Valid @ApiParam(name = "kNodeId", value = "知识点ID", required = true, readOnly = true) @RequestBody String kNodeId){
        return WebResult.okResult(kNodeService.findById(String.valueOf(JSONObject.parseObject(kNodeId).getString("kNodeId"))));
    }

    @ApiOperation(value = "根据知识点ID删除知识点(物理删除)")
    @PostMapping("/deleteByKNodeId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kNodeId", value = "知识点ID", example = "ff8081816782652001678265779b0000", dataType = "string", required = true)
    })
    public WebResult deleteByKNodeId(@Valid @ApiParam(name = "kNodeId", value = "知识点ID", required = true) @RequestBody String kNodeId){
        kNodeService.deleteById(String.valueOf(JSONObject.parseObject(kNodeId).getString("kNodeId")));
        return WebResult.okResult();
    }

    @ApiOperation(value = "改知识点为无效状态")
    @PostMapping("/deleteIsValidById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kNodeId", value = "知识点ID", example = "ff8081816782652001678265779b0000", dataType = "string", required = true)
    })
    public WebResult deleteIsValidById(@Valid @ApiParam(name = "kNodeId", value = "知识点ID", required = true) @RequestBody String kNodeId){
        kNodeService.deleteIsValidById(String.valueOf(JSONObject.parseObject(kNodeId).getString("kNodeId")));
        return WebResult.okResult();
    }
}
