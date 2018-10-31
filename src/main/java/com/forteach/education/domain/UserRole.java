package com.forteach.education.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 9:19
 */
@Data
@Entity
@Table(name = "user_role",indexes = {@Index(columnList = "role_id")})
@IdClass(UserRoleFundPrimarykey.class)
public class UserRole extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserRoleFundPrimarykey userRoleFundPrimarykey;

    private String userId;

    private String roleId;

}

@Data
@Embeddable
class UserRoleFundPrimarykey implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id",columnDefinition = "varchar(32) COMMENT '用户编号'",insertable = false,updatable = false)
    private String userId;

    @Column(name = "role_id",columnDefinition = "varchar(32) COMMENT '角色编号'",insertable = false,updatable = false)
    private String roleId;

    public UserRoleFundPrimarykey() {
    }

    public UserRoleFundPrimarykey(String userId, String roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
