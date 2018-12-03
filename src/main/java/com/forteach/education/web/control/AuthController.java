package com.forteach.education.web.control;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.SysUsers;
import com.forteach.education.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-2 22:17
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AuthController {
    @Resource
    private UserService userService;
    @PostMapping("/register")
    public WebResult registerUser(@RequestBody SysUsers user){
        return userService.registerUser(user);
    }
//    @PostMapping("/login")
//    public WebResult login(@RequestBody SysUsers sysUsers){
//
//    }
}
