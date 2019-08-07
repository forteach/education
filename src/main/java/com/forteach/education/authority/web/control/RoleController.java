package com.forteach.education.authority.web.control;

import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.authority.domain.SysRole;
import com.forteach.education.authority.service.ActionColumnService;
import com.forteach.education.authority.service.AuthorityMgrService;
import com.forteach.education.authority.service.RoleService;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.authority.web.req.ActionColumnReq;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.web.control.BaseController;
import com.forteach.education.web.vo.AuthorityVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@Api(value = "/role", tags = {"权限管理 角色权限 栏目权限 "})
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;

    @Resource
    private TokenService tokenService;

    @Resource
    private AuthorityMgrService authorityMgrService;

    @Resource
    private ActionColumnService actionColumnService;

    /**
     * 角色列表
     *
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "/list")
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
    @UserLoginToken
    @PostMapping(value = "/edit")
    @ApiOperation(value = "编辑/保存角色", notes = "编辑/保存角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色编号", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "remark", value = "备注 角色说明", dataType = "string", paramType = "form")
    })
    public WebResult editRole(@Valid @RequestBody @ApiParam(value = "角色数据", required = true) SysRole sysRole) {
        return WebResult.okResult(roleService.edit(sysRole));
    }

    /**
     * 删除角色
     *
     * @param authorityVo
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/remove")
    @ApiOperation(value = "删除角色", notes = "通过角色id删除角色(物理删除)")
    @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "string", required = true, paramType = "from")
    public WebResult removeRole(@RequestBody @ApiParam(value = "通过角色id删除角色", required = true) AuthorityVo authorityVo) {
        MyAssert.blank(authorityVo.getRoleId(), DefineCode.ERR0010, "角色id不为空");
        roleService.deleteRole(authorityVo.getRoleId());
        return WebResult.okResult();
    }

    /**
     * 获取当前角色的栏目
     *
     * @param authorityVo
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/col")
    @ApiOperation(value = "获取当前角色的栏目", notes = "通过角色id 获取当前角色的栏目")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "string", paramType = "query")
    public WebResult roleCol(@RequestBody @ApiParam(value = "通过角色id获得栏目", required = true) AuthorityVo authorityVo) {
        MyAssert.blank(authorityVo.getRoleId(), DefineCode.ERR0010, "角色id不为空");
        return WebResult.okResult(authorityMgrService.findColumnByRoleId(authorityVo.getRoleId()));
    }

    /**
     * 获取对应角色的栏目ID集合
     *
     * @param authorityVo
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/colIds")
    @ApiOperation(value = "获取对应角色的栏目ID集合", notes = "通过角色id 获取对应角色的栏目ID集合")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "string", paramType = "query")
    public WebResult roleColIds(@RequestBody @ApiParam(value = "通过角色id获得栏目id集合", required = true) AuthorityVo authorityVo) {
        MyAssert.blank(authorityVo.getRoleId(), DefineCode.ERR0010, "角色id不为空");
        return WebResult.okResult(authorityMgrService.findColumnIdsByRoleId(authorityVo.getRoleId()));
    }

    /**
     * 获取整个栏目树菜单
     *
     * @return
     */
    @UserLoginToken
    @GetMapping(value = "/treeMenu")
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
    @UserLoginToken
    @PostMapping(value = "/leafColOpera")
    @ApiOperation(value = "根据叶节点获得栏目操作", notes = "通过 colId 栏目id 获得栏目操作")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "string", paramType = "query")
    public WebResult colOperation(@RequestBody @ApiParam(value = "通过 colId 栏目id 获得栏目操作", required = true) AuthorityVo authorityVo) {
        MyAssert.blank(authorityVo.getColId(), DefineCode.ERR0010, "栏目id不为空");
        return WebResult.okResult(authorityMgrService.findColumnOperationByLeafNode(authorityVo.getColId()));
    }

    /**
     * 根据用户获得栏目操作列表
     *
     * @param authorityVo
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/col/operaList")
    @ApiOperation(value = "根据用户获得栏目操作列表", notes = "通过 userId 用户id 获得栏目操作列表")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "string", paramType = "query")
    public WebResult columnOperationList(@ApiParam(value = "通过 userId 用户id 获得栏目操作列表", required = true) @RequestBody AuthorityVo authorityVo) {
        MyAssert.blank(authorityVo.getUserId(), DefineCode.ERR0010, "用户id不为空");
        return WebResult.okResult(authorityMgrService.findColumnOperationListByUserId(authorityVo.getUserId()));
    }

    @UserLoginToken
    @GetMapping("/col/loginColumn")
    @ApiOperation(value = "获取当前登录的用户栏目操作列表", notes = "使用token获取当前登录的用户栏目操作列表")
    public WebResult loginColumn(HttpServletRequest request){
        String userId = tokenService.getUserId(request);
        return WebResult.okResult(authorityMgrService.findColumnOperationListByUserId(userId));
    }

    /**
     * 根据角色获得栏目列表级栏目操作
     *
     * @param authorityVo
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/col/operaAct")
    @ApiOperation(value = "根据角色获得栏目列表级栏目操作", notes = "通过 roleId 权限id 获得栏目列表级栏目操作")
    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "string", paramType = "query")
    public WebResult columnOperationAction(@RequestBody @ApiParam(value = "通过 roleId 权限id 获得栏目列表级栏目操作", required = true) AuthorityVo authorityVo) {
        MyAssert.blank(authorityVo.getRoleId(), DefineCode.ERR0010, "角色id不为空");
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "colId", value = "栏目id", required = true, dataType = "string", paramType = "query")
    })
    @UserLoginToken
    public WebResult findRoleColAct(@RequestBody @ApiParam(value = " 通过 roleId  colid 权限id，栏目id 获取角色栏目对应的动作", required = true) AuthorityVo authorityVo) {
        MyAssert.blank(authorityVo.getRoleId(), DefineCode.ERR0010, "角色id不为空");
        MyAssert.blank(authorityVo.getColId(), DefineCode.ERR0010, "栏目id不为空");
        return WebResult.okResult(authorityMgrService.findRoleColActIds(authorityVo.getRoleId(), authorityVo.getColId()));
    }

    /**
     * 保存对应角色栏目的动作
     *
     * @param params
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/saveRoleColAct")
    @ApiOperation(value = "保存对应角色栏目的动作", notes = "传入角色与权限list json 进行操作")
    public WebResult saveRoleColAct(@RequestBody @ApiParam(value = " json 串", required = true) String params) {
        MyAssert.blank(params, DefineCode.ERR0010, "参数不为空");
        authorityMgrService.saveRoleColAct(params);
        return WebResult.okResult();
    }

    @UserLoginToken
    @PostMapping("/saveEdit")
    @ApiOperation(value = "修改保存栏目信息", notes = "保存栏目时栏目编号(colId)不为空，修改栏目信息传 空字符串 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "colId", value = "栏目编号", dataType = "string", example = "121213dwe", paramType = "from"),
            @ApiImplicitParam(name = "colName", required = true, value = "栏目名称", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "colNameModel", required = true, value = "栏目跳转", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "colParentId", value = "父栏目编号", dataType = "string", example = "没有父栏目不用传数据", paramType = "form"),
            @ApiImplicitParam(name = "colParentName", value = "父栏目名称", dataType = "string", example = "没有父栏目不用传数据", paramType = "form"),
            @ApiImplicitParam(name = "colUrl", value = "链接地址", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "colImgUrl", value = "链接图标", required = true, dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "isOrder", value = "栏目顺序", dataType = "int", paramType = "form")
    })
    public WebResult saveEdit(@RequestBody ActionColumnReq actionColumnReq){
        return WebResult.okResult(actionColumnService.editSaveActionColumn(actionColumnReq));
    }
}
