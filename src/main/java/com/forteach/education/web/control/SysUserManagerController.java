package com.forteach.education.web.control;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.User;
import com.forteach.education.service.UserMgrService;
import com.forteach.education.web.vo.CastVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:38
 */
@Slf4j
@RestController
@RequestMapping(path = "/sysUserMgr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "/sysUserMgr", tags = "用户角色相关操作 ")
public class SysUserManagerController {

    @Resource
    private UserMgrService userMgrService;

    /**
     * @Description: 分配角色
     * @Param: castVo
     * @return:
     */
    @PostMapping(value = "/cast")
    @ApiOperation(value = "分配角色", notes = "分配角色")
    public WebResult cast(@Valid @RequestBody  @ApiParam(value = "分配角色", required = true) CastVo castVo) {
        userMgrService.updateUserRole(castVo.getRoleId(), castVo.getUserIds());
        return WebResult.okResult();
    }

    /**
     * @Description: 编辑用户
     * @Param: user
     * @return:
     */
    @PostMapping(value = "/edit")
    @ApiOperation(value = "编辑用户", notes = "编辑/保存用户")
    public WebResult edit(@Valid @RequestBody  @ApiParam(value = "编辑/保存用户", required = true)  User user) {
        return WebResult.okResult(userMgrService.edit(user));

    }


}
