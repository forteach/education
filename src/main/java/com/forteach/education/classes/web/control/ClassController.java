package com.forteach.education.classes.web.control;

import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.classes.domain.Classes;
import com.forteach.education.classes.service.ClassesService;
import com.forteach.education.classes.web.req.ClassesVo;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-11 11:14
 * @version: 1.0
 * @description:
 */
@RestController
@Api(value = "班级操作接口", tags = {"对班级进行相关查询，和编辑"})
@RequestMapping(path = "/class", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClassController {

    private final ClassesService classesService;

    public ClassController(ClassesService classesService) {
        this.classesService = classesService;
    }

    @GetMapping("/findAll")
    @UserLoginToken
    @ApiOperation(value = "查询所有班级信息")
    public WebResult findAll(){
        return WebResult.okResult(classesService.findAll());
    }

    @PostMapping("/editClass")
    @ApiOperation(value = "修改班级信息")
    @UserLoginToken
    public WebResult editClass(@RequestBody ClassesVo classesVo){
        MyAssert.isNull(classesVo.getClassId(), DefineCode.ERR0010, "班级编号不为空");
        Classes classes = classesService.editClass(classesVo);
        if (classes == null){
            MyAssert.isNull(null, DefineCode.OK, "需要修改的班级不存在");
        }
        return WebResult.okResult(classes);
    }

    @ApiOperation(value = "分页查询班级信息")
    @GetMapping(value = "/pageFindAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0"),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10"),
    })
    @UserLoginToken
    public WebResult pageFindAll(@RequestBody SortVo sortVo){
        MyAssert.blank(String.valueOf(sortVo.getPage()), DefineCode.ERR0010, "当前页码不为空");
        MyAssert.blank(String.valueOf(sortVo.getSize()), DefineCode.ERR0010, "每页数量不为空");
        return WebResult.okResult(classesService.pageAll(sortVo));
    }
}
