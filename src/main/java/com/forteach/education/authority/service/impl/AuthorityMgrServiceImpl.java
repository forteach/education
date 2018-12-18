package com.forteach.education.authority.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.authority.domain.ActionColumn;
import com.forteach.education.authority.domain.RoleColAct;
import com.forteach.education.authority.domain.UserRole;
import com.forteach.education.authority.repository.ActionColumnRepository;
import com.forteach.education.authority.repository.RoleColActRepository;
import com.forteach.education.authority.repository.UserRoleRepository;
import com.forteach.education.authority.service.AuthorityMgrService;
import com.forteach.education.web.vo.ColumnOperationVo;
import com.forteach.education.web.vo.OperationInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;
import static com.forteach.education.util.StringUtil.isEmpty;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 09:51
 */
@Slf4j
@Service
public class AuthorityMgrServiceImpl implements AuthorityMgrService {

    @Resource
    private ActionColumnRepository actionColumnRepository;

    @Resource
    private RoleColActRepository roleColActRepository;

    @Resource
    private UserRoleRepository userRoleRepository;

    /**
     * 栏目树菜单
     *
     * @return
     */
    @Override
    public List treeMenu() {
        return getInfoColChild(findTreeAll());
    }

    /**
     * 根据叶节点获取栏目操作
     *
     * @param colId
     * @return
     */
    @Override
    public List<ColumnOperationVo> findColumnOperationByLeafNode(String colId) {
        return actionColumnRepository.findColumnOperation(colId);
    }

    /**
     * 根据角色获取其对应的权限(栏目)
     *
     * @param roleId
     * @return
     */
    @Override
    public List findColumnByRoleId(String roleId) {

        //根据角色ID 获取栏目编号
        List<RoleColAct> roleColActList = roleColActRepository.findByRoleIdEquals(roleId);
        List<String> columnIdList = new ArrayList<>();

        roleColActList.forEach(roleColAct -> columnIdList.add(roleColAct.getColId()));

        //获取该角色对应的栏目
        return actionColumnRepository.findByColIdIn(columnIdList);
    }

    /**
     * 根据权限id获取栏目的id集合
     *
     * @param roleId
     * @return
     */
    @Override
    public Map<String, Object> findColumnIdsByRoleId(String roleId) {

        //根据角色ID 获取子栏目编号
        List<RoleColAct> roleColActList = roleColActRepository.findByRoleIdEquals(roleId);

        //新创建角色未分配权限
        if (roleColActList == null || roleColActList.size() == 0) {
            throw new RuntimeException("该角色尚未分配权限");
        }

        List<String> columnIdList = new ArrayList<>();
        roleColActList.forEach(roleColAct -> columnIdList.add(roleColAct.getColId()));

        //去除重复的colId
        HashSet<String> set = new HashSet<>(columnIdList);
        columnIdList.clear();
        columnIdList.addAll(set);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("colIds", columnIdList);
        List<Map<String, Object>> colInfoList = findColumnOperationByRoleId(roleId);
        List<Map<String, Object>> list = new ArrayList<>();
        colInfoList.forEach(m -> {
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", roleId);
            map.put("colId", m.get("colId"));
            map.put("actIds", m.get("actIds"));
            list.add(map);

        });
        resultMap.put("colAndActs", list);
        return resultMap;
    }

    /**
     * 根据角色获得栏目列表级栏目操作
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Map<String, Object>> findColumnOperationByRoleId(String roleId) {

        //获取该角色的所有子栏目信息
        List<OperationInfoVo> colInfoList = actionColumnRepository.findColumnOperationInfo(roleId);

        List<Map<String, Object>> listMap = new ArrayList<>();
        colInfoList.forEach(colInfoMap -> {

            String colId = colInfoMap.getColId();
            List<RoleColAct> rcaList = roleColActRepository.findByColIdAndRoleId(colId, roleId);

            List<String> list = new ArrayList<>();
            if (rcaList != null && rcaList.size() != 0) {
                rcaList.forEach(m -> {
                    list.add(m.getSysActId());
                });
            }

            Map<String, Object> map = new HashMap<>(10);
            map.put("actIds", list);
            map.put("colParentId", colInfoMap.getColParentId());
            map.put("colParentName", colInfoMap.getColParentName());
            map.put("colId", colInfoMap.getColId());
            map.put("colName", colInfoMap.getColName());

            listMap.add(map);
        });
        return listMap;
    }

    /**
     * 根据用户获得栏目操作列表
     *
     * @param userId
     * @return
     */
    @Override
    public List findColumnOperationListByUserId(String userId) {

        //获得用户对应的角色
        UserRole userRole = userRoleRepository.findByUserIdIs(userId);

        //根据角色编号找所有对应的叶节点栏目编号
        List<RoleColAct> roleColActList = roleColActRepository.findByRoleIdEquals(userRole.getRoleId());

        List<String> colIdList = new ArrayList<>();
        List<String> parentColId = new ArrayList<>();
        roleColActList.forEach(roleColAct -> colIdList.add(roleColAct.getColId()));

        //根据栏目编号找栏目
        List<ActionColumn> actionList = actionColumnRepository.findByColIdIn(colIdList);

        //找到对应的父栏目编号
        actionList.forEach(m -> parentColId.add(m.getColParentId()));

        return findInfoColChild(actionColumnRepository.findByColIdIn(parentColId), actionList);
    }

    /**
     * 查询没有父节点的顶级节点
     *
     * @return
     */
    @Override
    public List<ActionColumn> findTreeTop() {
        return actionColumnRepository.findByColIdIsNotNullAndColParentIdIsNull();
    }

    /**
     * 获取所有的树
     *
     * @return
     */
    @Override
    public List<ActionColumn> findTreeAll() {
        return actionColumnRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN);
    }

    /**
     * 获取角色栏目对应的动作
     *
     * @param roleId
     * @param colId
     * @return
     */
    @Override
    public List<String> findRoleColActIds(String roleId, String colId) {
        List<RoleColAct> roleColActList = roleColActRepository.findByColIdAndRoleId(colId, roleId);
        List<String> actIds = new ArrayList<>();
        roleColActList.forEach(m -> actIds.add(m.getSysActId()));
        return actIds;
    }

    /**
     * 保存对应角色对应栏目的动作
     *
     * @param json
     */
    @Override
    @Transactional( rollbackFor = Exception.class)
    public void saveRoleColAct(String json) {
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) JSONObject.parse(json);
        listMap.forEach(m -> {
            //删除该角色的所有子栏目
            roleColActRepository.deleteByRoleIdIs(String.valueOf(m.get("roleId")));
        });
        listMap.forEach(m -> {
            String[] actIds = m.get("actIds").toString().split(",");
            //保存最新修改的栏目动作
            for (String actId : actIds) {
                RoleColAct roleColAct = new RoleColAct();
                roleColAct.setRoleId(m.get("roleId").toString());
                roleColAct.setColId(m.get("colId").toString());
                roleColAct.setSysActId(actId);
                roleColAct.setCTime(new Date());
                roleColAct.setUTime(new Date());
                roleColActRepository.save(roleColAct);
            }
        });
    }

    /**
     * 递归获取栏目下级
     *
     * @param list
     * @return
     */
    @Override
    public List getInfoColChild(List<ActionColumn> list) {

        List<ActionColumn> treeList = new ArrayList<>();

        for (ActionColumn treeNode : list) {

            if (isEmpty(treeNode.getColParentId())) {
                treeList.add(treeNode);
            }

            for (ActionColumn it : list) {
                if (isEmpty(it.getColParentId())) {
                    continue;
                }
                if (it.getColParentId().equals(treeNode.getColId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return treeList;
    }

    @Override
    public List findInfoColChild(List<ActionColumn> topColumnList, List<ActionColumn> childrenList) {
        List<Map> listMap = new ArrayList<>();
        for (ActionColumn topColumn : topColumnList) {
            Map<String, Object> tempMap = new HashMap<>(10);
            Map<String, Object> metaMap = new HashMap<>(10);
            metaMap.put("title", topColumn.getColName());
            metaMap.put("icon", topColumn.getColImgUrl());
            tempMap.put("name", topColumn.getColNameModel());
            tempMap.put("path", topColumn.getColUrl());
            tempMap.put("meta", metaMap);
            List<Map> childList = new ArrayList<>();
            for (ActionColumn child : childrenList) {
                if (child.getColParentId().equals(topColumn.getColId())) {
                    Map<String, Object> childMap = new HashMap<>(10);
                    Map<String, Object> childMeta = new HashMap<>(10);
                    childMeta.put("title", child.getColName());
                    childMeta.put("icon", child.getColImgUrl());
                    childMap.put("name", child.getColNameModel());
                    childMap.put("path", child.getColUrl());
                    childMap.put("meta", childMeta);
                    childList.add(childMap);
                }
            }
            tempMap.put("children", childList);
            listMap.add(tempMap);
        }
        return listMap;
    }
}
