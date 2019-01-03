package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.databank.domain.ziliao.ViewDatum;
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
 * @Date: 2018/11/19 21:30
 * @Version: 1.0
 * @Description: 视频资料操作
 */
@RestController
@RequestMapping(path = "/viewDatum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "视频资料操作", tags = {"视频资源接口"})
public class ViewDatumController {

//    private final ViewDatumService viewDatumService;
//
//    @Autowired
//    public ViewDatumController(ViewDatumService viewDatumService) {
//        this.viewDatumService = viewDatumService;
//    }
//
//    @ApiOperation(value = "保存资源", notes = "保存视频资源链接信息")
//    @PostMapping("/save")
////    @JsonView(View.Summary.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "courseId", value = "科目课程ID", required = true, dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "viewName", value = "视频名称", required = true, dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "viewType", value = "视频类型", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "viewUrl", value = "视频URL", required = true, dataType = "string", paramType = "from")
//    })
//    public WebResult save(@Valid @ApiParam(name = "viewDatum", value = "视频资料对象", required = true) @RequestBody ViewDatum viewDatum){
//        return WebResult.okResult(viewDatumService.save(viewDatum));
//    }
//
//    @ApiOperation(value = "修改资源", notes = "修改资源信息")
//    @PostMapping("/edit")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "viewId", value = "视频编号", required = true, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "courseId", value = "科目课程ID", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "viewName", value = "视频名称", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "viewType", value = "视频类型", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "viewUrl", value = "视频URL", dataType = "string", paramType = "query")
//    })
//    public WebResult edit(@Valid @ApiParam(name = "viewDatum", value = "视频资料对象", required = true) @RequestBody ViewDatum viewDatum){
//        return WebResult.okResult(viewDatumService.edit(viewDatum));
//    }
//
//    @ApiOperation(value = "删除资源", notes = "删除资源对象")
//    @PostMapping("/delete")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "viewId", value = "视频编号", required = true, dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "courseId", value = "科目课程ID", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "viewName", value = "视频名称", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "viewType", value = "视频类型", dataType = "string", paramType = "query"),
//            @ApiImplicitParam(name = "viewUrl", value = "视频URL", dataType = "string", paramType = "query")
//    })
//    public WebResult delete(@Valid @ApiParam(name = "viewDatum", value = "删除资源对象", required = true) @RequestBody ViewDatum viewDatum){
//        viewDatumService.delete(viewDatum);
//        return WebResult.okResult();
//    }
//
//    @ApiOperation(value = "删除资源根据ID", notes = "根据视频资源ID 删除资源信息")
//    @PostMapping("/deleteById")
//    @ApiImplicitParam(name = "viewId", value = "viewId", dataType = "string", required = true, paramType = "query")
//    public WebResult deleteById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "viewId", value = "根据资源ID 删除对应资源信息", required = true) @RequestBody String viewId){
//        viewDatumService.deleteById(String.valueOf(JSONObject.parseObject(viewId).get("viewId")));
//        return WebResult.okResult();
//    }
//
//    @PostMapping("/getViewByViewId")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "viewId", value = "视频ID", dataType = "string", required = true, paramType = "query")
//    })
//    @ApiOperation(value = "查询资源", notes = "根据视频资源ID查询视频资源信息")
//    public WebResult getViewByViewId(@Valid @ApiParam(name = "viewId", value = "根据资源ID 查询对应资源信息", required = true) @RequestBody String viewId){
//        return WebResult.okResult(viewDatumService.getViewDatumById(String.valueOf(JSONObject.parseObject(viewId).getString("viewId"))));
//    }
//
////    @JsonView(View.SummaryDetail.class)
//    @ApiOperation(value = "分页查询全部", notes = "分页查询视频资源信息")
//    @PostMapping("/findAll")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0", paramType = "query"),
//            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10", paramType = "query"),
//            @ApiImplicitParam(value = "排序规则", dataType = "string", name = "sorting", example = "cTime", required = true, paramType = "query"),
//            @ApiImplicitParam(value = "有无效", name = "isValidated", dataType = "string",example = "0", required = true, paramType = "query"),
//            @ApiImplicitParam(value = "sort", name = "排序方式", dataType = "int", example = "1", paramType = "query")
//    })
//    public WebResult findAll(@Valid @ApiParam(name = "sortVo", value = "分页查询全部资源信息", required = true) @RequestBody SortVo sortVo){
//        return WebResult.okResult(viewDatumService.findAll(sortVo));
//    }
//
//    /**
//     * 通过视频资源 ID 逻辑删除视频资源信息
//     * @param viewId
//     * @return
//     */
//    @ApiOperation(value = "使其无效", notes = "删除视频信息(逻辑删除)")
//    @PostMapping("/deleteIsValidById")
//    @ApiImplicitParam(name = "viewId", value = "视频ID", dataType = "string", required = true, paramType = "query")
//    public WebResult deleteIsValidById(@Valid @NotBlank(message = "视频资源不为空") @ApiParam(name = "viewId", value = "根据资源ID 逻辑删除对应资源信息", required = true) @RequestBody String viewId){
//        viewDatumService.deleteIsValidById(viewId);
//        return WebResult.okResult();
//    }
//
////    @JsonView(View.Summary.class)
//    @ApiOperation(value = "根据章节ID查询视频信息", notes = "根据章节ID查询有效视频信息")
//    @PostMapping("/findByChapterId")
//    @ApiImplicitParam(name = "chapterId", value = "章节ID", dataType = "string", required = true, paramType = "query")
//    public WebResult findByChapterId(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "chapterId", value = "根据视频资源ID 查询视频信息", required = true) @RequestBody String chapterId){
//        return WebResult.okResult(viewDatumService.findByChapterId(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId"))));
//    }
}
