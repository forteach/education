package com.forteach.education.authority.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.authority.service.UserService;
import com.forteach.education.authority.web.req.RegisterUserReq;
import com.forteach.education.authority.web.req.UpdatePassWordReq;
import com.forteach.education.authority.web.req.UserLoginReq;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.MyAssert;
import com.forteach.education.common.keyword.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-2-22 13:45
 * @version: 1.0
 * @description:
 */
@Slf4j
@RestController
@Api(value = "用户认证", description = "教师端用户登录,注册，重置密码，等接口", tags = {"用户操作"})
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "passWord", value = "密码", required = true, dataType = "string", paramType = "from")
    })
    public WebResult login(@Valid @RequestBody UserLoginReq userLoginReq){
        return userService.login(userLoginReq);
    }

    @ApiOperation(value = "用户注册", notes = "用户只能是本校教职工才能注册")
    @PostMapping("/registerUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "passWord", value = "密码", dataType = "string", required = true, paramType = "from"),
            @ApiImplicitParam(name = "teacherCode", value = "教师代码", dataType = "string", required = true, paramType = "from")
    })
    public WebResult registerUser(@Valid @RequestBody RegisterUserReq registerUserReq){
        return userService.registerUser(registerUserReq);
    }

    @ApiOperation("重置教师账户密码为初始化密码")
    @PostMapping("/resetPassWord")
    @ApiImplicitParam(name = "teacherCode",value = "教师代码", required = true, dataType = "string",paramType = "from")
    @UserLoginToken
    public WebResult resetPassWord(@Valid @RequestBody String teacherCode){
        MyAssert.blank(teacherCode, DefineCode.ERR0010, "教师代码不为空");
        return userService.resetPassWord(JSONObject.parseObject(teacherCode).getString("teacherCode"));
    }

    @ApiOperation("添加教师用户信息用户账户")
    @PostMapping("/addSysTeacher")
    @ApiImplicitParam(name = "teacherCode",value = "教师代码", required = true, dataType = "string",paramType = "from")
    @UserLoginToken
    public WebResult addSysTeacher(@Valid @RequestBody String teacherCode){
        MyAssert.blank(teacherCode, DefineCode.ERR0010, "教师代码不为空");
        return userService.addSysTeacher(JSONObject.parseObject(teacherCode).getString("teacherCode"));
    }

    @ApiOperation("修改密码")
    @PostMapping("/updatePassWord")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "教师代码", name = "teacherCode", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(value = "旧密码", name = "oldPassWord", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(value = "新密码", name = "newPassWord", required = true, dataType = "string", paramType = "from")
    })
    @UserLoginToken
    public WebResult updatePassWord(@Valid @RequestBody UpdatePassWordReq updatePassWordReq){
        MyAssert.blank(updatePassWordReq.getTeacherCode(), DefineCode.ERR0010, "教师代码不为空");
        MyAssert.blank(updatePassWordReq.getOldPassWord(), DefineCode.ERR0010, "旧密码不能为空");
        MyAssert.blank(updatePassWordReq.getNewPassWord(), DefineCode.ERR0010, "新密码不能为空");
        return userService.updatePassWord(updatePassWordReq);
    }
}
