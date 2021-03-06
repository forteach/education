package com.forteach.education.course.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.domain.Specialty;
import com.forteach.education.course.service.SpecialtyService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

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
    @ApiImplicitParam(name = "specialtyName", value = "专业名称", required = true, dataType = "string", type = "string", paramType = "from", example = "电子商务")
    public WebResult save(@ApiParam(name = "specialtyName", value = "专业名称", required = true) @RequestBody String specialtyName) {
        MyAssert.blank(specialtyName, DefineCode.ERR0010, "专业名称不为空");
        return WebResult.okResult(specialtyService.save(String.valueOf(JSONObject.parseObject(specialtyName).getString("specialtyName"))));
    }


//    @PostMapping("/save")
//    @ApiOperation(value = "保存专业信息", notes = "保存专业信息")
//    @ApiImplicitParam(name = "specialtyName", value = "专业名称", required = true, dataType = "string", type = "string", paramType = "from", example = "电子商务")
//    public WebResult save(@ApiParam(name = "specialtyName", value = "专业名称", required = true)
//                              @Validated({SpecialtyRestVo.Add.class})
//                              @RequestBody SpecialtyRestVo specialtyRestVo,
//                          BindingResult result) {
//
//        //        MyAssert.blank(specialtyName, DefineCode.ERR0010, "专业名称不为空");
//        if (result.hasErrors()) {
//            return WebResult.failResult(DefineCode.ERR0002, result.getAllErrors());
//        }
//        return WebResult.okResult(specialtyService.save(String.valueOf(JSONObject.parseObject(specialtyRestVo.getName()).getString("specialtyName"))));
//    }

    @ApiOperation(value = "编辑专业信息", notes = "修改专业信息", response = Swagger2JacksonModule.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specialtyId", value = "主键ID", required = true, type = "string", paramType = "query"),
            @ApiImplicitParam(name = "specialtyName", value = "专业名称", required = true, type = "string", paramType = "body")
    })
    @PostMapping("/edit")
    public WebResult edit(@ApiParam(name = "specialty", value = "专业修改", required = true) @RequestBody Specialty specialty) {
        MyAssert.blank(specialty.getSpecialtyId(), DefineCode.ERR0010, "专业id不为空");
        return WebResult.okResult(specialtyService.edit(specialty));
    }

    @ApiOperation(value = "删除专业信息", notes = "删除专业信息(物理删除)")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specialtyId", value = "主键ID", required = true, type = "string", paramType = "query"),
            @ApiImplicitParam(name = "specialtyName", value = "专业名称", required = true, type = "string", paramType = "body")
    })
    public WebResult delete(@ApiParam(name = "specialty", value = "专业删除", required = true) @RequestBody Specialty specialty) {
        MyAssert.blank(specialty.getSpecialtyId(), DefineCode.ERR0010, "专业id不为空");
        return WebResult.okResult(specialtyService.edit(specialty));
    }

    /**
     * 删除专业信(物理删除)
     *
     * @param specialtyId
     * @return
     */
    @ApiOperation(value = "删除专业信息", notes = "根据　ID 删除专业信息(物理删除)")
    @PostMapping("/deleteById")
    @ApiImplicitParam(name = "specialtyId", value = "主键ID", required = true, type = "string", paramType = "query")
    public WebResult deleteById(@ApiParam(name = "specialtyId", value = "根据id删除相关专业", type = "string", required = true) @RequestBody String specialtyId) {
        MyAssert.blank(specialtyId, DefineCode.ERR0010, "专业id不为空");
        specialtyService.deleteById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId")));
        return WebResult.okResult();
    }

    /**
     * 逻辑删除专业信息使其无效不显示(逻辑删除)
     *
     * @param specialtyId
     */
    @ApiOperation(value = "删除专业信息", notes = "根据专业　ID 删除专业信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    @ApiImplicitParam(name = "specialtyId", value = "主键ID", required = true, type = "string", paramType = "query")
    public WebResult deleteIsValidById(@ApiParam(name = "specialtyId", type = "string", value = "专业id", required = true) @RequestBody String specialtyId) {
        MyAssert.blank(specialtyId, DefineCode.ERR0010, "专业id不为空");
        specialtyService.deleteIsValidById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId")));
        return WebResult.okResult();
    }

    /**
     * 分页查询科目信息
     *
     * @param sortVo
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0"),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10"),
            @ApiImplicitParam(value = "排序规则", dataType = "string", name = "sorting", example = "cTime", required = true),
            @ApiImplicitParam(value = "有无效", name = "isValidated", dataType = "string", example = "0", required = true),
            @ApiImplicitParam(value = "sort", name = "排序方式", dataType = "int", example = "1")
    })
    public WebResult findAll(@ApiParam(value = "分页对象", name = "sortVo", required = true) @RequestBody SortVo sortVo) {
        MyAssert.blank(String.valueOf(sortVo.getPage()), DefineCode.ERR0010, "当前页码不为空");
        MyAssert.blank(String.valueOf(sortVo.getSize()), DefineCode.ERR0010, "每页数量不为空");
        return WebResult.okResult(specialtyService.findAll(sortVo));
    }

    @ApiOperation(value = "查询专业信息", notes = "根据ID查询专业信息")
    @PostMapping("/getSpecialtyById")
    @ApiImplicitParam(name = "specialtyId", value = "专业主键ID", required = true, dataType = "string", paramType = "from")
    public WebResult getSpecialtyById(@ApiParam(value = "专业id", name = "specialtyId", type = "string", required = true) @RequestBody String specialtyId) {
        MyAssert.blank(specialtyId, DefineCode.ERR0010, "专业主键ID 不为空");
        return WebResult.okResult(specialtyService.getSpecialtyById(String.valueOf(JSONObject.parseObject(specialtyId).get("specialtyId"))));
    }
}
