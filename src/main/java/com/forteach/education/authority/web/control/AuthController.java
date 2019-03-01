package com.forteach.education.authority.web.control;

import com.forteach.education.authority.service.UserService;
import com.forteach.education.authority.web.req.RegisterUserReq;
import com.forteach.education.authority.web.req.UserLoginReq;
import com.forteach.education.common.keyword.WebResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "passWord", value = "密码", required = true, dataType = "string", paramType = "from")
    })
    public WebResult login(@Valid @RequestBody UserLoginReq userLoginReq){
        return userService.login(userLoginReq);
    }

    @PostMapping("/registerUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "passWord", value = "密码", dataType = "string", required = true, paramType = "from"),
            @ApiImplicitParam(name = "teacherCode", value = "教师代码", dataType = "string", required = true, paramType = "from")
    })
    public WebResult registerUser(@Valid @RequestBody RegisterUserReq registerUserReq){
        return userService.registerUser(registerUserReq);
    }
}
