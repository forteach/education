package com.forteach.education.service;

import com.forteach.education.domain.ActionColumn;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 09:51
 */
public interface AuthorityMgrService {
    /**
     * 栏目树菜单
     *
     * @return
     */
    List treeMenu();

    /**
     * 根据叶节点获取栏目操作
     *
     * @param colId
     * @return
     */
    List<Map<String, Object>> findColumnOperationByLeafNode(String colId);

    /**
     * 根据角色获取其对应的权限(栏目)
     *
     * @param roleId
     * @return
     */
    List findColumnByRoleId(String roleId);

    /**
     * 根据权限id获取栏目的id集合
     *
     * @param roleId
     * @return
     */
    Map<String, Object> findColumnIdsByRoleId(String roleId);

    /**
     * 根据角色获得栏目列表级栏目操作
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> findColumnOperationByRoleId(String roleId);

    /**
     * 根据用户获得栏目操作列表
     *
     * @param userId
     * @return
     */
    List findColumnOperationListByUserId(String userId);

    /**
     * 查询没有父节点的顶级节点
     *
     * @return
     */
    List findTreeTop();

    /**
     * 获取角色栏目对应的动作
     *
     * @param roleId
     * @param colId
     * @return
     */
    List<Integer> findRoleColActIds(String roleId, String colId);

    /**
     * 保存对应角色对应栏目的动作
     *
     * @param json
     */
    void saveRoleColAct(String json);

    /**
     * 递归获取栏目下级
     *
     * @param list
     * @return
     */
    List getInfoColChild(List<ActionColumn> list);

    List findInfoColChild(List<ActionColumn> topColumnList, List<ActionColumn> childrenList);
}
