package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.ChapteData;
import com.forteach.education.filter.View;
import com.forteach.education.service.ChapteDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 15:43
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping(path = "/chapteData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "章节资料", tags = {"章节资料操作信息"})
public class ChapteDataController {

    private final ChapteDataService chapteDataService;

    @Autowired
    public ChapteDataController(ChapteDataService chapteDataService) {
        this.chapteDataService = chapteDataService;
    }

    @ApiOperation(value = "保存资料信息")
    @PostMapping("/save")
    @JsonView(View.SummaryExtend.class)
    public WebResult webResult(@Valid @ApiParam(value = "保存资料信息", name = "chapteData") @RequestBody ChapteData chapteData){
        return WebResult.okResult(chapteDataService.save(chapteData));
    }

    @ApiOperation(value = "修改资源信息")
    @PostMapping("/edit")
    @JsonView(View.SummaryExtend.class)
    public WebResult edit(@Valid @ApiParam(value = "修改资料信息", name = "chapteData") @RequestBody ChapteData chapteData){
        return WebResult.okResult(chapteDataService.edit(chapteData));
    }

    @ApiOperation(value = "删除科目章节资料信息")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(value = "删除资料信息", name = "chapteData") @RequestBody ChapteData chapteData){
        chapteDataService.delete(chapteData);
        return WebResult.okResult();
    }

    @PostMapping("/findById")
    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "根据资料ID查询资料信息", notes = "根据资料ID查询资料信息")
    public WebResult findById(@Valid @ApiParam(value = "根据dataId查询对应资料信息", name = "dataId", type = "string") @RequestBody String dataId){
        return WebResult.okResult(chapteDataService.findById(String.valueOf(JSONObject.parseObject(dataId).getString("dataId"))));
    }

    @PostMapping("/deleteById")
    @ApiOperation(value = "删除资料信息", notes = "根据ID 删除资源表")
    public WebResult deleteById(@Valid @ApiParam(value = "根据dataId删除对应资料信息", name = "dataId", type = "string") @RequestBody String dataId){
        chapteDataService.deleteById(String.valueOf(JSONObject.parseObject(dataId).getString("dataId")));
        return WebResult.okResult();
    }

    @ApiOperation(value = "根据科目ID查询对应资料信息", notes = "根据科目ID查询对应资料信息")
    @PostMapping("/findByCourseId")
    @JsonView(View.SummaryExtend.class)
    public WebResult findByCourseId(@Valid @ApiParam(value = "科目ID", name = "courseId", type = "string") @RequestBody String courseId){
        return WebResult.okResult(chapteDataService.findById(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }


    @ApiOperation(value = "根据章节ID查询资料信息", notes = "根据章节ID查询资料信息")
    @PostMapping("/findByChapterId")
    @JsonView(View.SummaryExtend.class)
    public WebResult findByChapterId(@Valid @ApiParam(value = "章节ID", name = "chapterId", type = "string") @RequestBody String chapterId){
        return WebResult.okResult(chapteDataService.findByChapterId(String.valueOf(JSONObject.parseObject(chapterId).getString("chapterId"))));
    }

}
