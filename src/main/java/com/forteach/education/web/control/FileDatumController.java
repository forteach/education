package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.databank.domain.ziliao.FileDatum;
import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 21:20
 * @Version: 1.0
 * @Description:
 */
//@RestController
//@RequestMapping(path = "/fileDatum", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@Api(value = "文件资料操作", tags = {"文件资源接口"})
//public class FileDatumController {
//
//    @Resource
//    private  FileDatumService fileDatumService;
//
//
//    @ApiOperation(value = "保存文件信息", notes = "保存文件资源文件信息")
//    @PostMapping("/save")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "courseId", value = "科目编号", required = true, dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", required = true, dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileName", value = "文件名称", required = true, dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileType", value = "文件类型", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileUrl", value = "文件URL", required = true, dataType = "string", paramType = "from")
//    })
//    public WebResult save(@Valid @ApiParam(name = "fileDatum", value = "文件资料对象", required = true) @RequestBody FileDatum fileDatum) {
//        return WebResult.okResult(fileDatumService.save(fileDatum));
//    }
//
//    @ApiOperation(value = "修改文件信息", notes = "修改资源信息")
//    @PostMapping("/edit")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fileId", value = "文件ID", dataType = "string", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileName", value = "文件名称", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileType", value = "文件类型", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileUrl", value = "文件URL", dataType = "string", paramType = "from")
//    })
//    public WebResult edit(@Valid @ApiParam(name = "fileDatum", value = "文件资料对象", required = true) @RequestBody FileDatum fileDatum) {
//        return WebResult.okResult(fileDatumService.edit(fileDatum));
//    }
//
//    @ApiOperation(value = "删除文件信息", notes = "删除资源对象")
//    @PostMapping("/delete")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "fileId", value = "文件ID", dataType = "string", required = true, paramType = "query"),
//            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileName", value = "文件名称", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileType", value = "文件类型", dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "fileUrl", value = "文件URL", dataType = "string", paramType = "from")
//    })
//    public WebResult delete(@Valid @ApiParam(name = "fileDatum", value = "删除资源对象", required = true) @RequestBody FileDatum fileDatum) {
//        fileDatumService.delete(fileDatum);
//        return WebResult.okResult();
//    }
//
//    @ApiOperation(value = "删除文件信息", notes = "根据文件资源ID 删除资源信息")
//    @PostMapping("/deleteById")
//    @ApiImplicitParam(name = "fileId", value = "文件ID", required = true, dataType = "string", paramType = "query")
//    public WebResult deleteById(@Valid @ApiParam(name = "fileId", value = "根据资源ID 删除对应资源信息", type = "string", required = true) @RequestBody String fileId) {
//        fileDatumService.deleteById(String.valueOf(JSONObject.parseObject(fileId).get("fileId")));
//        return WebResult.okResult();
//    }
//
//    @PostMapping("/getFileByFileId")
////    @JsonView(View.SummaryExtend.class)
//    @ApiOperation(value = "获取文件信息", notes = "根据文件资源ID查询文件资源信息")
//    @ApiImplicitParam(name = "fileId", value = "文件ID", required = true, dataType = "string", paramType = "query")
//    public WebResult getFileByFileId(@Valid @ApiParam(name = "fileId", value = "根据资源ID 删除对应资源信息", type = "string", required = true) @RequestBody String fileId) {
//        return WebResult.okResult(fileDatumService.getFileDatumById(String.valueOf(JSONObject.parseObject(fileId).get("fileId"))));
//    }
//
//    @ApiOperation(value = "分页查询", notes = "分页查询文件资源信息")
//    @PostMapping("/findAll")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0"),
//            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10"),
//            @ApiImplicitParam(value = "排序规则", dataType = "string", name = "sorting", example = "cTime", required = true),
//            @ApiImplicitParam(value = "有无效", name = "isValidated", dataType = "string", example = "0", required = true),
//            @ApiImplicitParam(value = "sort", name = "排序方式", dataType = "int", example = "1")
//    })
//    public WebResult findAll(@Valid @ApiParam(name = "sortVo", value = "分页查询文件资源信息", required = true) @RequestBody SortVo sortVo) {
//        return WebResult.okResult(fileDatumService.findAll(sortVo));
//    }
//
//    /**
//     * 通过文件资源 ID 逻辑删除文件资源信息
//     *
//     * @param fileId
//     * @return
//     */
//    @ApiOperation(value = "使其无效", notes = "删除文件信息(逻辑删除)")
//    @PostMapping("/deleteIsValidById")
//    @ApiImplicitParam(name = "fileId", value = "文件ID", required = true, dataType = "string", paramType = "query")
//    public WebResult deleteIsValidById(@Valid @ApiParam(name = "fileId", value = "根据资源ID 逻辑删除对应资源信息", type = "string", required = true) @RequestBody String fileId) {
//        fileDatumService.deleteIsValidById(fileId);
//        return WebResult.okResult();
//    }
//
//    @ApiOperation(value = "根据章节ID查询文件信息", notes = "根据章节ID查询有效音频信息")
//    @PostMapping("/findByChapterId")
////    @JsonView(View.SummaryExtend.class)
//    @ApiImplicitParam(name = "chapterId", value = "根据章节ID查询文件信息", dataType = "string", required = true, paramType = "query")
//    public WebResult findByChapterId(@Valid @NotNull(message = "ID不为空") @ApiParam(name = "chapterId", value = "根据章节 ID 查询文件信息", required = true) @RequestBody String chapterId) {
//        return WebResult.okResult(fileDatumService.findByChapterId(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId"))));
//    }
//}
