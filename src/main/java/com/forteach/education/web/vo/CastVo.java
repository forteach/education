package com.forteach.education.web.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 3:09
 */
@Data
public class CastVo {
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 用户ID集合
     */
    private List<String> userIds;
}
