package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.Specialty;
import com.forteach.education.service.SpecialtyService;
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
 * @Date: 18-11-22 15:06
 * @Version: 1.0
 * @Description: 专业管理
 */
@RestController
@RequestMapping("/specialty")
@Api(value = "专业操作", tags = {"专业操作"})
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @ApiOperation(value = "保存专业信息", notes = "保存专业信息")
    @PostMapping("/save")
    public WebResult save(@Valid @ApiParam(name = "specialtyName", value = "专业名称", required = true) @RequestBody String specialtyName){
        return WebResult.okResult(specialtyService.save(String.valueOf(JSONObject.parseObject(specialtyName).getString("specialtyName"))));
    }

    @ApiOperation(value = "编辑专业信息", notes = "修改专业信息")
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(name = "specialty", value = "专业修改", required = true) @RequestBody Specialty specialty){
        return WebResult.okResult(specialtyService.edit(specialty));
    }

    @ApiOperation(value = "删除专业信息", notes = "删除专业信息(物理删除)")
    @PostMapping("/delete")
    public WebResult delete(@Valid @ApiParam(name = "specialty", value = "专业删除", required = true) @RequestBody Specialty specialty){
        return WebResult.okResult(specialtyService.edit(specialty));
    }

    /**
     * 删除专业信(物理删除)
     * @param specialtyId
     * @return
     */
    @ApiOperation(value = "删除专业信息", notes = "根据　ID 删除专业信息(物理删除)")
    @PostMapping("/deleteById")
    public WebResult deleteById(@Valid @ApiParam(name = "specialtyId", value = "根据id删除相关专业", type = "string", required = true) @RequestBody String specialtyId){
        specialtyService.deleteById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId")));
        return WebResult.okResult();
    }

    /**
     * 逻辑删除专业信息使其无效不显示(逻辑删除)
     * @param specialtyId
     */
    @ApiOperation(value = "删除专业信息", notes = "根据专业　ID 删除专业信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    public WebResult deleteIsValidById(@Valid @ApiParam(name = "specialtyId", type = "string", value = "专业id", required = true) @RequestBody String specialtyId){
        specialtyService.deleteIsValidById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId")));
        return WebResult.okResult();
    }

    /**
     * 分页查询科目信息
     * @param sortVo
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/findAll")
    public WebResult findAll(@Valid @ApiParam(value = "分页对象", name = "sortVo", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(specialtyService.findAll(sortVo));
    }

    @ApiOperation(value = "查询专业信息", notes = "根据ID查询专业信息")
    @PostMapping("/getSpecialtyById")
    public WebResult getSpecialtyById(@Valid @ApiParam(value = "专业id", name = "specialtyId", type = "string", required = true) @RequestBody String specialtyId){
        return WebResult.okResult(specialtyService.getSpecialtyById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId"))));
    }
}
