package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.FileDatum;
import com.forteach.education.service.FileDatumService;
import com.forteach.education.web.vo.SortVo;
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
 * @Date: 2018/11/20 21:20
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/fileDatum")
@Api(value = "文件资料操作", tags = {"文件资源接口"})
public class FileDatumController {
    @Autowired
    private FileDatumService fileDatumService;

    @ApiOperation(value = "fileDatum", notes = "保存文件资源文件信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(value = "fileDatum", name = "文件资料对象", required = true) @RequestBody FileDatum fileDatum){
        return WebResult.okResult(fileDatumService.save(fileDatum));
    }

    @ApiOperation(value = "fileDatum", notes = "修改资源信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(value = "fileDatum", name = "文件资料对象", required = true) @RequestBody FileDatum fileDatum){
        return WebResult.okResult(fileDatumService.edit(fileDatum));
    }

    @ApiOperation(value = "delete", notes = "删除资源对象")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(value = "fileDatum", name = "删除资源对象", required = true) @RequestBody FileDatum fileDatum){
        fileDatumService.delete(fileDatum);
        return WebResult.okResult();
    }

    @ApiOperation(value = "fileId", notes = "根据文件资源ID 删除资源信息")
    @PostMapping("/deleteById")
    public WebResult deleteById(@Valid @ApiParam(value = "FileId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String fileId){
        fileDatumService.deleteById(String.valueOf(JSONObject.parseObject(fileId).get("fileId")));
        return WebResult.okResult();
    }

    @PostMapping("/getFileByFileId")
    @ApiOperation(value = "fileId", notes = "根据文件资源ID查询文件资源信息")
    public WebResult getFileByFileId(@Valid @ApiParam(value = "fileId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String fileId){
        return WebResult.okResult(fileDatumService.getFileDatumById(String.valueOf(JSONObject.parseObject(fileId).get("fileId"))));
    }

    @ApiOperation(value = "sortVo", notes = "分页查询文件资源信息")
    @PostMapping("/findAll")
    public WebResult findAll(@Valid @ApiParam(value = "sortVo", name = "分页查询势派资源信息", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(fileDatumService.findAll(sortVo));
    }

    /**
     * 通过文件资源 ID 逻辑删除文件资源信息
     * @param fileId
     * @return
     */
    @ApiOperation(value = "fileId", notes = "删除文件信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    public WebResult deleteIsValidById(@Valid @ApiParam(value = "fileId", name = "根据资源ID 逻辑删除对应资源信息", required = true) @RequestBody String fileId){
        fileDatumService.deleteIsValidById(fileId);
        return WebResult.okResult();
    }
}