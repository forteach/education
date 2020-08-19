package com.forteach.education.service;

import com.forteach.education.authority.domain.SysUsers;
import com.forteach.education.authority.web.req.SysUserEditReq;

import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:39
 */
public interface UserMgrService {
    /**
     * 分配角色
     * @param roleId
     * @param userIds
     */
    void updateUserRole(String roleId, List<String> userIds);

    /**
     * 编辑/保存用户
     * @param user
     * @return
     */
    SysUsers edit(SysUserEditReq user);
}
