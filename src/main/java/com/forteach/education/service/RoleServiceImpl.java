package com.forteach.education.service;

import com.forteach.education.domain.SysRole;
import com.forteach.education.domain.User;
import com.forteach.education.exception.RoleException;
import com.forteach.education.repository.SysRoleRepository;
import com.forteach.education.repository.UserRepository;
import com.forteach.education.repository.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.forteach.education.common.Dic.TAKE_EFFECT_OPEN;

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
    public List<SysRole> findRoleInfo() {
        return sysRoleRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN);
    }

    /**
     * 用户列表
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findUsersInfo(int page, int size, String sorting) {

        Sort sort = new Sort(Sort.Direction.DESC, sorting);

        Page<User> userList = userRepository.findByIsValidatedEquals(TAKE_EFFECT_OPEN, PageRequest.of(page, size, sort));
        List<Map<String, Object>> list = new ArrayList<>();

        userList.forEach(m -> {
            Map<String, Object> resultMap = new HashMap<>(10);
            resultMap.put("userId", m.getId());
            resultMap.put("userName", m.getUserName());
            resultMap.put("email", m.getEmail());
            resultMap.put("account", m.getAccount());
            resultMap.put("introduction", m.getIntroduction());
            resultMap.put("register_phone", m.getRegisterPhone());
            list.add(resultMap);
        });
        return list;
    }

    /**
     * 删除角色
     *
     * @param roleId
     * @return
     */
    @Override
    public void deleteRole(String roleId) {

        userRoleRepository.deleteByRoleId(roleId);
        sysRoleRepository.deleteByRoleId(roleId);
    }

    /**
     * 编辑角色(存在则更新,不存在则保存)
     *
     * @param role
     * @return
     */
    @Override
    public SysRole edit(SysRole role) {
        SysRole sysRole = sysRoleRepository.findByRoleId(role.getRoleId());
        role.setUTime(new Date());
        if (sysRole == null) {
            editCheck(role);
            role.setCTime(new Date());
        } else {
            role.setCTime(sysRole.getCTime());
        }
        return sysRoleRepository.save(role);
    }

    /**
     * 根据角色编号获得角色
     *
     * @param roleId
     * @return
     */
    @Override
    public SysRole findById(String roleId) {
        return sysRoleRepository.findByRoleId(roleId);
    }

    /**
     * 判断角色名称是否存在
     *
     * @param roleName
     * @return
     */
    @Override
    public boolean existsName(String roleName) {
        return sysRoleRepository.findSysRoleByRoleNameAndIsValidated(roleName, TAKE_EFFECT_OPEN) != null;
    }

    private void editCheck(SysRole role) {
        if (existsName(role.getRoleName())) {
            throw new RoleException("已有相同角色名");
        }
    }

}
