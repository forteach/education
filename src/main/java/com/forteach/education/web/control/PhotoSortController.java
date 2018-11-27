package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.PhotoSort;
import com.forteach.education.service.PhotoSortService;
import com.forteach.education.web.vo.PhotoSortVo;
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
 * @Date: 18-11-27 08:53
 * @Version: 1.0
 * @Description:
 */
@RestController
@RequestMapping("/photoSort")
@Api(value = "课件图册操作", tags = "{课件图册操作}")
public class PhotoSortController {

    private final PhotoSortService photoSortService;

    @Autowired
    public PhotoSortController(PhotoSortService photoSortService) {
        this.photoSortService = photoSortService;
    }

    @ApiOperation(value = "保存图册信息", notes = "保存图册及其描述信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(value = "保存图册", type = "photoSortVo", name = "photoSortVo", required = true) @RequestBody PhotoSortVo photoSortVo){
        return WebResult.okResult(photoSortService.save(photoSortVo));
    }

    @PostMapping("/findById")
    @ApiOperation(value = "查询图册信息", notes = "根据图册编号查询对应的图册信息")
    public WebResult findById(@Valid @ApiParam(value = "查询图册信息", type = "string", required = true, name = "sortImgId") @RequestBody String sortImgId){
        return WebResult.okResult(photoSortService.findById(String.valueOf(JSONObject.parseObject(sortImgId).getString("sortImgId"))));
    }

    @ApiOperation(value = "删除图册信息", notes = "根据图册 ID 删除数据库保存的图册信息")
    @PostMapping(value = "/deleteById")
    public WebResult deleteById(@Valid @ApiParam(value = "删除图册信息", type = "string", required = true, name = "sortImgId") @RequestBody String sortImgId){
        photoSortService.deleteById(String.valueOf(JSONObject.parseObject(sortImgId).getString("sortImgId")));
        return WebResult.okResult();
    }

    @PostMapping(value = "/edit")
    @ApiOperation(value = "修改图册信息", notes = "修改图册信息")
    public WebResult edit(@Valid @ApiParam(value = "保存图册", type = "photoSort", name = "photoSort", required = true) @RequestBody PhotoSort photoSort){
        return WebResult.okResult(photoSortService.edit(photoSort));
    }

}
