package com.forteach.education.web.control;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.SysRole;
import com.forteach.education.service.AuthorityMgrService;
import com.forteach.education.service.RoleService;
import com.forteach.education.web.vo.AuthorityVo;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: 权限相关控制器
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:15
 */
@Slf4j
@RestController
@RequestMapping(path = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    @Resource
    private AuthorityMgrService authorityMgrService;

    /**
     * 角色列表
     *
     * @return
     */
    @PostMapping(value = "/list")
    public WebResult list() {
        return WebResult.okResult(roleService.findRoleInfo());
    }

    /**
     * 编辑(保存)角色
     *
     * @param sysRole
     * @return
     */
    @PostMapping(value = "/edit")
    public WebResult editRole(@RequestBody SysRole sysRole) {
        return WebResult.okResult(roleService.edit(sysRole));
    }

    /**
     * 用户列表
     *
     * @return
     */
    @PostMapping(value = "/users")
    public WebResult userList(@RequestBody SortVo sortVo) {
        return WebResult.okResult(roleService.findUsersInfo(sortVo.getPage(), sortVo.getSize(), sortVo.getSorting()));
    }

    /**
     * 删除角色
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/remove")
    public WebResult removeRole(@RequestBody AuthorityVo authorityVo) {
        roleService.deleteRole(authorityVo.getRoleId());
        return WebResult.okResult();
    }

    /**
     * 获取当前角色的栏目
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/col")
    public WebResult roleCol(@RequestBody AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnByRoleId(authorityVo.getRoleId()));
    }

    /**
     * 获取对应角色的栏目ID集合
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/colIds")
    public WebResult roleColIds(@RequestBody AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnIdsByRoleId(authorityVo.getRoleId()));
    }

    /**
     * 获取整个栏目树菜单
     *
     * @return
     */
    @PostMapping(value = "/treeMenu")
    public WebResult colTreeMenu() {
        return WebResult.okResult(authorityMgrService.treeMenu());
    }

    /**
     * 根据叶节点获得栏目操作
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/leafColOpera")
    public WebResult colOperation(@RequestBody AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnOperationByLeafNode(authorityVo.getColId()));
    }

    /**
     * 根据用户获得栏目操作列表
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/col/operaList")
    public WebResult ColumnOperationList(@RequestBody AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnOperationListByUserId(authorityVo.getUserId()));
    }

    /**
     * 根据角色获得栏目列表级栏目操作
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/col/operaAct")
    public WebResult columnOperationAction(@RequestBody AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnOperationByRoleId(authorityVo.getRoleId()));
    }

    /**
     * 获取角色栏目对应的动作
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/actIds")
    public WebResult findRoleColAct(@RequestBody AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findRoleColActIds(authorityVo.getRoleId(), authorityVo.getColId()));
    }

    /**
     * 保存对应角色栏目的动作
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/saveRoleColAct")
    public WebResult saveRoleColAct(@RequestBody String params) {
        authorityMgrService.saveRoleColAct(params);
        return WebResult.okResult();
    }

}
