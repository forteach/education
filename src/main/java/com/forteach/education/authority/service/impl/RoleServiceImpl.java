package com.forteach.education.authority.service.impl;

import com.forteach.education.authority.domain.SysRole;
import com.forteach.education.authority.domain.SysUsers;
import com.forteach.education.authority.repository.SysRoleRepository;
import com.forteach.education.authority.repository.UserRepository;
import com.forteach.education.authority.repository.UserRoleRepository;
import com.forteach.education.authority.service.RoleService;
import com.forteach.education.exception.RoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 10:17
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleRepository userRoleRepository;

    /**
     * 角色列表
     *
     * @return
     */
    @Override
    @Cacheable(value = "sysRoles", key = "#root.targetClass", unless = "#result eq null")
    public List<SysRole> findRoleInfo() {
        return sysRoleRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN);
    }

    /**
     * 用户列表
     *
     * @return
     */
    @Override
    @Cacheable(value = "allUsersPage", key = "#root.targetClass", unless = "#result eq null")
    public Page<SysUsers> findUsersInfo(int page, int size) {
        Page<SysUsers> userList = userRepository.findByIsValidatedEqualsOrderByCreateTimeDesc(TAKE_EFFECT_OPEN, PageRequest.of(page, size));
        userList.forEach(m -> {
            m.setPassWord(null);
        });
        return userList;
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "sysRoles", allEntries = true, beforeInvocation = true)
    public void deleteRole(String roleId) {

        userRoleRepository.deleteByRoleId(roleId);
        sysRoleRepository.deleteByRoleId(roleId);
    }

    /**
     * 根据角色编号获得角色
     *
     * @param roleId
     * @return
     */
    @Override
    @Cacheable(value = "sysRole", key = "#roleId", unless = "#result eq null")
    public SysRole findById(String roleId) {
        return sysRoleRepository.findByRoleId(roleId);
    }

    /**
     * 判断角色名称是否存在
     *
     * @param roleName
     * @return
     */
    private boolean existsName(String roleName) {
        return sysRoleRepository.findSysRoleByRoleNameAndIsValidated(roleName, TAKE_EFFECT_OPEN) != null;
    }

    private void editCheck(SysRole role) {
        if (existsName(role.getRoleName())) {
            throw new RoleException("已有相同角色名");
        }
    }

    /**
     * 编辑角色(存在则更新,不存在则保存)
     *
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "sysRoles", key = "#root.targetClass", unless = "#result eq null ")
    public SysRole edit(SysRole role) {
        SysRole sysRole = sysRoleRepository.findByRoleId(role.getRoleId());
        if (sysRole == null) {
            editCheck(role);
        }
        return sysRoleRepository.save(role);
    }

}
