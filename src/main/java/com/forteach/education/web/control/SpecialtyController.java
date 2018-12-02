package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.common.WebResult;
import com.forteach.education.domain.Specialty;
import com.forteach.education.filter.View;
import com.forteach.education.service.SpecialtyService;
import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 15:06
 * @Version: 1.0
 * @Description: 专业管理
 */
@RestController
@RequestMapping(path = "/specialty", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "专业操作", tags = {"专业操作"})
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @Autowired
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存专业信息", notes = "保存专业信息")
    @ApiImplicitParam(name = "specialtyName", value = "专业名称", required = true, dataType = "string", type = "string", paramType = "body", example = "电子商务")
    @JsonView(View.SummaryExtend.class)
    public WebResult save(@Valid @NotBlank(message = "专业名称不为空") @ApiParam(name = "specialtyName", value = "专业名称", required = true) @RequestBody String specialtyName){
        return WebResult.okResult(specialtyService.save(String.valueOf(JSONObject.parseObject(specialtyName).getString("specialtyName"))));
    }

    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "编辑专业信息", notes = "修改专业信息", response = Swagger2JacksonModule.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specialtyId", value = "主键ID", required = true, type = "string", paramType = "query"),
            @ApiImplicitParam(name = "specialtyName", value = "专业名称", required = true, type = "string", paramType = "body")
    })
    @PostMapping("/edit")
    public WebResult edit(@Valid @ApiParam(name = "specialty", value = "专业修改", required = true) @RequestBody Specialty specialty){
        return WebResult.okResult(specialtyService.edit(specialty));
    }

    @ApiOperation(value = "删除专业信息", notes = "删除专业信息(物理删除)")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specialtyId", value = "主键ID", required = true, type = "string", paramType = "query"),
            @ApiImplicitParam(name = "specialtyName", value = "专业名称", required = true, type = "string", paramType = "body")
    })
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
    @ApiImplicitParam(name = "specialtyId", value = "主键ID", required = true, type = "string", paramType = "query")
    public WebResult deleteById(@Valid @NotEmpty(message = "专业ID不为空") @ApiParam(name = "specialtyId", value = "根据id删除相关专业", type = "string", required = true) @RequestBody String specialtyId){
        specialtyService.deleteById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId")));
        return WebResult.okResult();
    }

    /**
     * 逻辑删除专业信息使其无效不显示(逻辑删除)
     * @param specialtyId
     */
    @ApiOperation(value = "删除专业信息", notes = "根据专业　ID 删除专业信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    @ApiImplicitParam(name = "specialtyId", value = "主键ID", required = true, type = "string", paramType = "query")
    public WebResult deleteIsValidById(@Valid @NotEmpty(message = "专业id不为空") @ApiParam(name = "specialtyId", type = "string", value = "专业id", required = true) @RequestBody String specialtyId){
        specialtyService.deleteIsValidById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId")));
        return WebResult.okResult();
    }

    /**
     * 分页查询科目信息
     * @param sortVo
     * @return
     */
    @JsonView(View.SummaryDetail.class)
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0"),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10"),
            @ApiImplicitParam(value = "排序规则", dataType = "string", name = "sorting", example = "cTime", required = true),
            @ApiImplicitParam(value = "有无效", name = "isValidated", dataType = "string",example = "0", required = true),
            @ApiImplicitParam(value = "sort", name = "排序方式", dataType = "int", example = "1")
    })
    public WebResult findAll(@Valid @ApiParam(value = "分页对象", name = "sortVo", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(specialtyService.findAll(sortVo));
    }

    @JsonView(View.SummaryDetail.class)
    @ApiOperation(value = "查询专业信息", notes = "根据ID查询专业信息")
    @PostMapping("/getSpecialtyById")
    @ApiImplicitParam(name = "specialtyId", value = "专业主键ID", required = true, dataType = "string", paramType = "from")
    public WebResult getSpecialtyById(@Valid @ApiParam(value = "专业id", name = "specialtyId", type = "string", required = true) @RequestBody String specialtyId){
        return WebResult.okResult(specialtyService.getSpecialtyById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId"))));
    }
}
