package com.forteach.education.service;

import com.forteach.education.domain.ActionColumn;
import com.forteach.education.repository.ActionColumnRepository;
import com.forteach.education.web.vo.ColumnOperationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;
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
        return null;
    }
}
