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
@RequestMapping("/FileDatum")
@Api(value = "文件资料操作", tags = {"文件资源接口"})
public class FileDatumController {
    @Autowired
    private FileDatumService FileDatumService;

    @ApiOperation(value = "FileDatum", notes = "保存文件资源文件信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(value = "FileDatum", name = "文件资料对象", required = true) @RequestBody FileDatum FileDatum){
        return WebResult.okResult(FileDatumService.save(FileDatum));
    }

    @ApiOperation(value = "FileDatum", notes = "修改资源信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(value = "FileDatum", name = "文件资料对象", required = true) @RequestBody FileDatum FileDatum){
        return WebResult.okResult(FileDatumService.edit(FileDatum));
    }

    @ApiOperation(value = "delete", notes = "删除资源对象")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(value = "FileDatum", name = "删除资源对象", required = true) @RequestBody FileDatum FileDatum){
        FileDatumService.delete(FileDatum);
        return WebResult.okResult();
    }

    @ApiOperation(value = "FileId", notes = "根据文件资源ID 删除资源信息")
    @PostMapping("/deleteById")
    public WebResult deleteById(@Valid @ApiParam(value = "FileId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String FileId){
        FileDatumService.deleteById(String.valueOf(JSONObject.parseObject(FileId).get("FileId")));
        return WebResult.okResult();
    }

    @PostMapping("/getFileByFileId")
    @ApiOperation(value = "FileId", notes = "根据文件资源ID查询文件资源信息")
    public WebResult getFileByFileId(@Valid @ApiParam(value = "FileId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String FileId){
        return WebResult.okResult(FileDatumService.getFileDatumById(String.valueOf(JSONObject.parseObject(FileId).get("FileId"))));
    }

    @ApiOperation(value = "sortVo", notes = "分页查询文件资源信息")
    @PostMapping("/findAll")
    public WebResult findAll(@Valid @ApiParam(value = "sortVo", name = "分页查询势派资源信息", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(FileDatumService.findAll(sortVo));
    }

    /**
     * 通过文件资源 ID 逻辑删除文件资源信息
     * @param FileId
     * @return
     */
    @ApiOperation(value = "FileId", notes = "删除文件信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    public WebResult deleteIsValidById(@Valid @ApiParam(value = "FileId", name = "根据资源ID 逻辑删除对应资源信息", required = true) @RequestBody String FileId){
        FileDatumService.deleteIsValidById(FileId);
        return WebResult.okResult();
    }
}
