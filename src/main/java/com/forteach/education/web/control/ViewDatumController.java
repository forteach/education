package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.ViewDatum;
import com.forteach.education.service.ViewDatumService;
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
 * @Date: 2018/11/19 21:30
 * @Version: 1.0
 * @Description: 视频资料操作
 */
@RestController
@RequestMapping("/viewDatum")
@Api(value = "视频资料操作", tags = {"视频资源接口"})
public class ViewDatumController {

    @Autowired
    private ViewDatumService viewDatumService;

    @ApiOperation(value = "viewDatum", notes = "保存视频资源链接信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(value = "viewDatum", name = "视频资料对象", required = true) @RequestBody ViewDatum viewDatum){
        return WebResult.okResult(viewDatumService.save(viewDatum));
    }

    @ApiOperation(value = "viewDatum", notes = "修改资源信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(value = "viewDatum", name = "视频资料对象", required = true) @RequestBody ViewDatum viewDatum){
        return WebResult.okResult(viewDatumService.edit(viewDatum));
    }

    @ApiOperation(value = "delete", notes = "删除资源对象")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(value = "viewDatum", name = "删除资源对象", required = true) @RequestBody ViewDatum viewDatum){
        viewDatumService.delete(viewDatum);
        return WebResult.okResult();
    }

    @ApiOperation(value = "viewId", notes = "根据视频资源ID 删除资源信息")
    @PostMapping("/deleteById")
    public WebResult deleteById(@Valid @ApiParam(value = "viewId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String viewId){
        viewDatumService.deleteById(String.valueOf(JSONObject.parseObject(viewId).get("viewId")));
        return WebResult.okResult();
    }

    @PostMapping("/getViewByViewId")
    @ApiOperation(value = "viewId", notes = "根据视频资源ID查询视频资源信息")
    public WebResult getViewByViewId(@Valid @ApiParam(value = "viewId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String viewId){
        return WebResult.okResult(viewDatumService.getViewDatumById(String.valueOf(JSONObject.parseObject(viewId).get("viewId"))));
    }

    @ApiOperation(value = "sortVo", notes = "分页查询视频资源信息")
    @PostMapping("/findAll")
    public WebResult findAll(@Valid @ApiParam(value = "sortVo", name = "分页查询势派资源信息", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(viewDatumService.findAll(sortVo));
    }

    /**
     * 通过视频资源 ID 逻辑删除视频资源信息
     * @param viewId
     * @return
     */
    @ApiOperation(value = "viewId", notes = "删除视频信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    public WebResult deleteIsValidById(@Valid @ApiParam(value = "viewId", name = "根据资源ID 逻辑删除对应资源信息", required = true) @RequestBody String viewId){
        viewDatumService.deleteIsValidById(viewId);
        return WebResult.okResult();
    }
}
