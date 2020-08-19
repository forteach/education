package com.forteach.education.authority.web.control;

import com.forteach.education.authority.service.TokenService;
import com.forteach.education.authority.web.req.FindAllPageStudentReq;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-11-26 17:54
 * @version: 1.0
 * @description:
 */
@RestController
@RequestMapping(path = "/student", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "学生controller", tags = {"学生操作接口"})
public class StudentController {
    private final StudentService studentService;
    protected final TokenService tokenService;

    public StudentController(StudentService studentService, TokenService tokenService) {
        this.studentService = studentService;
        this.tokenService = tokenService;
    }

    @ApiOperation(value = "分页查询学生信息")
    @PostMapping(path = "/findAllPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentName", value = "学生名称", dataType = "string"),
            @ApiImplicitParam(name = "className", value = "班级名称", dataType = "string"),
            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0"),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10"),
    })
    public WebResult findAllPage(@RequestBody FindAllPageStudentReq req) {
        MyAssert.blank(String.valueOf(req.getPage()), DefineCode.ERR0010, "当前页码不为空");
        MyAssert.blank(String.valueOf(req.getSize()), DefineCode.ERR0010, "每页数量不为空");
        return WebResult.okResult(studentService.findAllPage(req));
    }
}
