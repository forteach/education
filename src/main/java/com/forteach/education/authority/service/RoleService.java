package com.forteach.education.authority.service;

import com.forteach.education.authority.domain.SysRole;
import com.forteach.education.authority.domain.SysUsers;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:16
 */
public interface RoleService {
    /**
     * 角色列表
     *
     * @return
     */
    List<SysRole> findRoleInfo();

    /**
     * 用户列表
     *
     * @param page    分页位
     * @param size    分页数量
     * @return
     */
    Page<SysUsers> findUsersInfo(int page, int size);

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    void deleteRole(String roleId);

    /**
     * 编辑角色(存在则更新,不存在则保存)
     *
     * @param role
     * @return
     */
    SysRole edit(SysRole role);

    /**
     * 根据角色编号获得角色
     *
     * @param roleId
     * @return
     */
    SysRole findById(String roleId);

}
