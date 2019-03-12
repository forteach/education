package com.forteach.education.authority.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.authority.service.RoleService;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.authority.service.UserService;
import com.forteach.education.authority.web.req.UpdatePassWordReq;
import com.forteach.education.authority.web.req.UserLoginReq;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    private final RoleService roleService;

    private final TokenService tokenService;

    public AuthController(UserService userService, RoleService roleService, TokenService tokenService){
        this.userService = userService;
        this.roleService = roleService;
        this.tokenService = tokenService;
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "teacherCode", value = "教师代码", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "passWord", value = "密码", required = true, dataType = "string", paramType = "from")
    })
    public WebResult login(@Valid @RequestBody UserLoginReq userLoginReq){
        MyAssert.blank(userLoginReq.getTeacherCode(), DefineCode.ERR0010, "教师代码不为空");
        MyAssert.blank(userLoginReq.getPassWord(), DefineCode.ERR0010, "密码不为空");
        return userService.login(userLoginReq);
    }

//    @ApiOperation(value = "用户注册", notes = "用户只能是本校教职工才能注册")
//    @PostMapping("/registerUser")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "from"),
//            @ApiImplicitParam(name = "passWord", value = "密码", dataType = "string", required = true, paramType = "from"),
//            @ApiImplicitParam(name = "teacherCode", value = "教师代码", dataType = "string", required = true, paramType = "from")
//    })
//    public WebResult registerUser(@Valid @RequestBody RegisterUserReq registerUserReq){
//        return userService.registerUser(registerUserReq);
//    }

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
            @ApiImplicitParam(value = "旧密码", name = "oldPassWord", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(value = "新密码", name = "newPassWord", required = true, dataType = "string", paramType = "from")
    })
    @UserLoginToken
    public WebResult updatePassWord(@Valid @RequestBody UpdatePassWordReq updatePassWordReq, HttpServletRequest request){
        MyAssert.blank(updatePassWordReq.getOldPassWord(), DefineCode.ERR0010, "旧密码不能为空");
        MyAssert.blank(updatePassWordReq.getNewPassWord(), DefineCode.ERR0010, "新密码不能为空");
        updatePassWordReq.setTeacherCode(tokenService.getUserId(request));
        return userService.updatePassWord(updatePassWordReq);
    }

    @UserLoginToken
    @ApiOperation("修改教师用户状态")
    @GetMapping("/updateState")
    @ApiImplicitParam(name = "teacherCode", value = "教师代码", required = true, dataType = "string", paramType = "from")
    public WebResult updateState(@RequestBody String teacherCode){
        MyAssert.blank(teacherCode, DefineCode.ERR0010, "教师代码不为空");
        String teacherCodeStr = JSONObject.parseObject(teacherCode).getString("teacherCode");
        userService.updateState(teacherCodeStr);
        return WebResult.okResult();
    }

    /**
     * 用户列表
     *
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/users")
    @ApiOperation(value = "用户列表", notes = "通过 分页 及排序获得用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0"),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10")
    })
    public WebResult userList(@Valid @RequestBody @ApiParam(value = "分页对象", required = true) SortVo sortVo) {
        return WebResult.okResult(roleService.findUsersInfo(sortVo.getPage(), sortVo.getSize()));
    }
}
