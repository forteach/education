package com.forteach.education.service;

import com.forteach.education.domain.ActionColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 09:51
 */
@Slf4j
@Service
public class AuthorityMgrServiceImpl implements AuthorityMgrService {

    /**
     * 栏目树菜单
     *
     * @return
     */
    @Override
    public List treeMenu() {
        return null;
    }

    /**
     * 根据叶节点获取栏目操作
     *
     * @param colId
     * @return
     */
    @Override
    public List<Map<String, Object>> findColumnOperationByLeafNode(String colId) {
        return null;
    }

    /**
     * 根据角色获取其对应的权限(栏目)
     *
     * @param roleId
     * @return
     */
    @Override
    public List findColumnByRoleId(String roleId) {
        return null;
    }

    /**
     * 根据权限id获取栏目的id集合
     *
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> findColumnIdsByRoleId(String roleId) {
        return null;
    }

    /**
     * 根据角色获得栏目列表级栏目操作
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Map<String, Object>> findColumnOperationByRoleId(String roleId) {
        return null;
    }

    /**
     * 根据用户获得栏目操作列表
     *
     * @param userId
     * @return
     */
    @Override
    public List findColumnOperationListByUserId(String userId) {
        return null;
    }

    /**
     * 查询没有父节点的顶级节点
     *
     * @return
     */
    @Override
    public List findTreeTop() {
        return null;
    }

    /**
     * 获取角色栏目对应的动作
     *
     * @param roleId
     * @param colId
     * @return
     */
    @Override
    public List<Integer> findRoleColActIds(String roleId, String colId) {
        return null;
    }

    /**
     * 保存对应角色对应栏目的动作
     *
     * @param json
     */
    @Override
    public void saveRoleColAct(String json) {

    }

    /**
     * 递归获取栏目下级
     *
     * @param list
     * @return
     */
    @Override
    public List getInfoColChild(List<ActionColumn> list) {
        return null;
    }

    @Override
    public List findInfoColChild(List<ActionColumn> topColumnList, List<ActionColumn> childrenList) {
        return null;
    }
}
