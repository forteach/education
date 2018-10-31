package com.forteach.education.service;

import com.forteach.education.domain.SysRole;

import java.util.List;
import java.util.Map;

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
     * @param page 分页位
     * @param size 分页数量
     * @param sorting 排序（倒叙）
     * @return
     */
    List<Map<String, Object>> findUsersInfo(int page, int size, String sorting);

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

    /**
     * 判断角色名称是否存在
     *
     * @param roleName
     * @return
     */
    boolean existsName(String roleName);
}
