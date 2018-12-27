package com.forteach.education.web.control;

import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.authority.domain.SysRole;
import com.forteach.education.authority.service.AuthorityMgrService;
import com.forteach.education.authority.service.RoleService;
import com.forteach.education.web.vo.AuthorityVo;
import com.forteach.education.common.web.vo.SortVo;
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
 * @Description: 权限相关控制器
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:15
 */
@Slf4j
@RestController
@RequestMapping(path = "/role", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "/role", tags = "权限管理 角色权限 栏目权限 ")
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
    @ApiOperation(value = "获得权限角色列表", notes = "获取所有的权限角色列表")
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
    @ApiOperation(value = "编辑/保存角色", notes = "编辑/保存角色")
    public WebResult editRole(@Valid @RequestBody  @ApiParam(value = "角色数据", required = true) SysRole sysRole) {
        return WebResult.okResult(roleService.edit(sysRole));
    }

    /**
     * 用户列表
     *
     * @return
     */
    @PostMapping(value = "/users")
    @ApiOperation(value = "用户列表", notes = "通过 分页 及排序获得用户列表")
    public WebResult userList(@Valid @RequestBody @ApiParam(value = "分页对象", required = true) SortVo sortVo) {
        return WebResult.okResult(roleService.findUsersInfo(sortVo.getPage(), sortVo.getSize(), sortVo.getSorting()));
    }

    /**
     * 删除角色
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/remove")
    @ApiOperation(value = "删除角色", notes = "通过角色id删除角色")
    public WebResult removeRole(@Valid @RequestBody @ApiParam(value = "通过角色id删除角色", required = true) AuthorityVo authorityVo) {
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
    @ApiOperation(value = "获取当前角色的栏目", notes = "通过角色id 获取当前角色的栏目")
    public WebResult roleCol(@Valid @RequestBody @ApiParam(value = "通过角色id获得栏目", required = true) AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnByRoleId(authorityVo.getRoleId()));
    }

    /**
     * 获取对应角色的栏目ID集合
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/colIds")
    @ApiOperation(value = "获取对应角色的栏目ID集合", notes = "通过角色id 获取对应角色的栏目ID集合")
    public WebResult roleColIds(@Valid @RequestBody @ApiParam(value = "通过角色id获得栏目id集合", required = true) AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnIdsByRoleId(authorityVo.getRoleId()));
    }

    /**
     * 获取整个栏目树菜单
     *
     * @return
     */
    @PostMapping(value = "/treeMenu")
    @ApiOperation(value = "获取整个栏目树菜单", notes = "获取整个栏目树菜单")
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
    @ApiOperation(value = "根据叶节点获得栏目操作", notes = "通过 colId 栏目id 获得栏目操作")
    public WebResult colOperation(@Valid @RequestBody @ApiParam(value = "通过 colId 栏目id 获得栏目操作", required = true) AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnOperationByLeafNode(authorityVo.getColId()));
    }

    /**
     * 根据用户获得栏目操作列表
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/col/operaList")
    @ApiOperation(value = "根据用户获得栏目操作列表", notes = "通过 userId 用户id 获得栏目操作列表")
    public WebResult columnOperationList(@Valid @RequestBody @ApiParam(value = "通过 userId 用户id 获得栏目操作列表", required = true) AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnOperationListByUserId(authorityVo.getUserId()));
    }

    /**
     * 根据角色获得栏目列表级栏目操作
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/col/operaAct")
    @ApiOperation(value = "根据角色获得栏目列表级栏目操作", notes = "通过 roleId 权限id 获得栏目列表级栏目操作")
    public WebResult columnOperationAction(@Valid @RequestBody @ApiParam(value = "通过 roleId 权限id 获得栏目列表级栏目操作", required = true) AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findColumnOperationByRoleId(authorityVo.getRoleId()));
    }

    /**
     * 获取角色栏目对应的动作
     *
     * @param authorityVo
     * @return
     */
    @PostMapping(value = "/actIds")
    @ApiOperation(value = "获取角色栏目对应的动作", notes = "通过 roleId  colid 权限id，栏目id 获取角色栏目对应的动作")
    public WebResult findRoleColAct(@Valid @RequestBody @ApiParam(value = " 通过 roleId  colid 权限id，栏目id 获取角色栏目对应的动作", required = true) AuthorityVo authorityVo) {
        return WebResult.okResult(authorityMgrService.findRoleColActIds(authorityVo.getRoleId(), authorityVo.getColId()));
    }

    /**
     * 保存对应角色栏目的动作
     *
     * @param params
     * @return
     */
    @PostMapping(value = "/saveRoleColAct")
    @ApiOperation(value = "保存对应角色栏目的动作", notes = "传入角色与权限list json 进行操作")
    public WebResult saveRoleColAct(@RequestBody @ApiParam(value = " json 串", required = true)  String params) {
        authorityMgrService.saveRoleColAct(params);
        return WebResult.okResult();
    }

}
