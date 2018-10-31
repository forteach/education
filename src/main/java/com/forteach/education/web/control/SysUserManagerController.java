package com.forteach.education.web.control;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.User;
import com.forteach.education.service.UserMgrService;
import com.forteach.education.web.vo.CastVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:38
 */
@Slf4j
@RestController
@RequestMapping(path = "/sysUserMgr", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysUserManagerController {

    @Resource
    private UserMgrService userMgrService;

    /**
     * @Description: 分配角色
     * @Param: castVo
     * @return:
     */
    @PostMapping(value = "/cast")
    public WebResult cast(@RequestBody CastVo castVo) {
        userMgrService.updateUserRole(castVo.getRoleId(), castVo.getUserIds());
        return WebResult.okResult();
    }

    /**
     * @Description: 编辑用户
     * @Param: user
     * @return:
     */
    @PostMapping(value = "/edit")
    public WebResult edit(@RequestBody User user) {
        return WebResult.okResult(userMgrService.edit(user));

    }


}
