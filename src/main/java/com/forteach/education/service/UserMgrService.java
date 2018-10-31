package com.forteach.education.service;

import com.forteach.education.domain.User;

import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:39
 */
public interface UserMgrService {
    /**
     *
     * @param roleId
     * @param userIds
     */
    void updateUserRole(String roleId, List<String> userIds);

    User edit(User user);
}
