package com.forteach.education.authority.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:50
 */
@Data
@Embeddable
public class UserRoleFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id", columnDefinition = "VARCHAR(32) COMMENT '用户编号'", insertable = false, updatable = false)
    private String userId;

    @Column(name = "role_id", columnDefinition = "VARCHAR(32) COMMENT '角色编号'", insertable = false, updatable = false)
    private String roleId;

    public UserRoleFundPrimarykey() {
    }

    public UserRoleFundPrimarykey(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
