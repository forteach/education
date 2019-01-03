package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.databank.domain.ziliao.LinkDatum;
import com.forteach.education.filter.View;
import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 21:23
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(path = "/linkDatum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "链接资料操作", tags = {"链接资源接口"})
public class LinkDatumController {

//    private final LinkDatumService linkDatumService;
//
//    @Autowired
//    public LinkDatumController(LinkDatumService linkDatumService) {
//        this.linkDatumService = linkDatumService;
//    }
//
//    @ApiOperation(value = "保存链接信息", notes = "保存链接资源链接信息")
//    @PostMapping("/save")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", required = true, dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "linkUrl", value = "链接URL", required = true, dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "linkName", value = "链接名称", required = true, dataType = "string", paramType = "from")
//    })
//    public WebResult save(@Valid @ApiParam(name = "linkDatum", value = "链接资料对象", required = true) @RequestBody LinkDatum linkDatum){
//        return WebResult.okResult(linkDatumService.save(linkDatum));
//    }
//
//    @ApiOperation(value = "修改链接信息", notes = "修改资源信息")
//    @PostMapping("/edit")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "linkId", value = "链接ID", dataType = "string", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "linkUrl", value = "链接URL", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "linkName", value = "链接名称", dataType = "string", paramType = "from")
//    })
//    public WebResult edit(@Valid @ApiParam(name = "linkDatum", value = "链接资料对象", required = true) @RequestBody LinkDatum linkDatum){
//        return WebResult.okResult(linkDatumService.edit(linkDatum));
//    }
//
//    @ApiOperation(value = "删除链接信息", notes = "删除资源对象")
//    @PostMapping("/delete")
//    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "linkId", value = "链接ID", dataType = "string", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "linkUrl", value = "链接URL", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "linkName", value = "链接名称", dataType = "string", paramType = "from")
//    })
//    public WebResult delete(@Valid @ApiParam(name = "linkDatum", value = "删除资源对象", required = true) @RequestBody LinkDatum linkDatum){
//        linkDatumService.delete(linkDatum);
//        return WebResult.okResult();
//    }
//
//    @ApiOperation(value = "删除链接信息", notes = "根据链接资源ID 删除资源信息")
//    @PostMapping("/deleteById")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParam(name = "linkId", value = "链接ID", dataType = "string", required = true, paramType = "query")
//    public WebResult deleteById(@Valid @ApiParam(name = "linkId", value = "根据资源ID 删除对应资源信息", type = "string", required = true) @RequestBody String linkId){
//        linkDatumService.deleteById(String.valueOf(JSONObject.parseObject(linkId).get("LinkId")));
//        return WebResult.okResult();
//    }
//
//    @PostMapping("/getLinkByLinkId")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParam(name = "linkId", value = "链接ID", dataType = "string", required = true, paramType = "query")
//    @ApiOperation(value = "删除链接信息", notes = "根据链接资源ID查询链接资源信息")
//    public WebResult getLinkByLinkId(@Valid @ApiParam(name = "linkId", value = "根据资源ID 删除对应资源信息", type = "string", required = true) @RequestBody String linkId){
//        return WebResult.okResult(linkDatumService.getLinkDatumById(String.valueOf(JSONObject.parseObject(linkId).get("linkId"))));
//    }
//
//    @ApiOperation(value = "分页信息", notes = "分页查询链接资源信息")
//    @PostMapping("/findAll")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0"),
//            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10"),
//            @ApiImplicitParam(value = "排序规则", dataType = "string", name = "sorting", example = "cTime", required = true),
//            @ApiImplicitParam(value = "有无效", name = "isValidated", dataType = "string",example = "0", required = true),
//            @ApiImplicitParam(value = "sort", name = "排序方式", dataType = "int", example = "1")
//    })
//    public WebResult findAll(@Valid @ApiParam(name = "sortVo", value = "分页查询势派资源信息", required = true) @RequestBody SortVo sortVo){
//        return WebResult.okResult(linkDatumService.findAll(sortVo));
//    }
//
//    /**
//     * 通过链接资源 ID 逻辑删除链接资源信息
//     * @param linkId
//     * @return
//     */
//    @ApiOperation(value = "使其无效", notes = "删除链接信息(逻辑删除)")
//    @PostMapping("/deleteIsValidById")
//    @ApiImplicitParam(name = "linkId", value = "链接ID", dataType = "string", required = true, paramType = "query")
//    public WebResult deleteIsValidById(@Valid @ApiParam(value = "linkId", name = "根据资源ID 逻辑删除对应资源信息", type = "string", required = true) @RequestBody String linkId){
//        linkDatumService.deleteIsValidById(linkId);
//        return WebResult.okResult();
//    }
//
//    @ApiOperation(value = "根据章节ID查询链接信息", notes = "根据章节ID查询有效链接信息")
//    @PostMapping("/findByChapterId")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParam(name = "chapterId", value = "章节ID", dataType = "string", required = true, paramType = "query")
//    public WebResult findByChapterId(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "chapterId", value = "根据章节 ID 查询链接信息", required = true) @RequestBody String chapterId){
//        return WebResult.okResult(linkDatumService.findByChapterId(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId"))));
//    }
}
