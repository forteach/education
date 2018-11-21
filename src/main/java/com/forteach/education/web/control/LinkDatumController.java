package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.LinkDatum;
import com.forteach.education.service.LinkDatumService;
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
 * @Date: 2018/11/20 21:23
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/linkDatum")
@Api(value = "链接资料操作", tags = {"链接资源接口"})
public class LinkDatumController {

    @Autowired
    private LinkDatumService linkDatumService;

    @ApiOperation(value = "linkDatum", notes = "保存链接资源链接信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(value = "LinkDatum", name = "链接资料对象", required = true) @RequestBody LinkDatum linkDatum){
        return WebResult.okResult(linkDatumService.save(linkDatum));
    }

    @ApiOperation(value = "linkDatum", notes = "修改资源信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(value = "LinkDatum", name = "链接资料对象", required = true) @RequestBody LinkDatum linkDatum){
        return WebResult.okResult(linkDatumService.edit(linkDatum));
    }

    @ApiOperation(value = "delete", notes = "删除资源对象")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(value = "linkDatum", name = "删除资源对象", required = true) @RequestBody LinkDatum linkDatum){
        linkDatumService.delete(linkDatum);
        return WebResult.okResult();
    }

    @ApiOperation(value = "linkId", notes = "根据链接资源ID 删除资源信息")
    @PostMapping("/deleteById")
    public WebResult deleteById(@Valid @ApiParam(value = "LinkId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String linkId){
        linkDatumService.deleteById(String.valueOf(JSONObject.parseObject(linkId).get("LinkId")));
        return WebResult.okResult();
    }

    @PostMapping("/getLinkByLinkId")
    @ApiOperation(value = "linkId", notes = "根据链接资源ID查询链接资源信息")
    public WebResult getLinkByLinkId(@Valid @ApiParam(value = "linkId", name = "根据资源ID 删除对应资源信息", required = true) @RequestBody String linkId){
        return WebResult.okResult(linkDatumService.getLinkDatumById(String.valueOf(JSONObject.parseObject(linkId).get("linkId"))));
    }

    @ApiOperation(value = "sortVo", notes = "分页查询链接资源信息")
    @PostMapping("/findAll")
    public WebResult findAll(@Valid @ApiParam(value = "sortVo", name = "分页查询势派资源信息", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(linkDatumService.findAll(sortVo));
    }

    /**
     * 通过链接资源 ID 逻辑删除链接资源信息
     * @param linkId
     * @return
     */
    @ApiOperation(value = "linkId", notes = "删除链接信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    public WebResult deleteIsValidById(@Valid @ApiParam(value = "linkId", name = "根据资源ID 逻辑删除对应资源信息", required = true) @RequestBody String linkId){
        linkDatumService.deleteIsValidById(linkId);
        return WebResult.okResult();
    }
}
