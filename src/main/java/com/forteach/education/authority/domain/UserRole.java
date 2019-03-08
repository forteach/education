package com.forteach.education.authority.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @Description:　用户角色
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 9:19
 */
@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_role", indexes = {@Index(columnList = "role_id", name = "role_id_index"),
        @Index(name = "user_id_index", columnList = "user_id")})
@IdClass(UserRoleFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "user_role", comment = "用户角色")
public class UserRole extends Entitys {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserRoleFundPrimarykey userRoleFundPrimarykey;

    private String userId;

    private String roleId;

    public UserRole() {
    }

    public UserRole(UserRoleFundPrimarykey userRoleFundPrimarykey, String userId, String roleId) {
        this.userRoleFundPrimarykey = userRoleFundPrimarykey;
        this.userId = userId;
        this.roleId = roleId;
    }
}
