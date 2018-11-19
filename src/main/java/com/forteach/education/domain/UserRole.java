package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　用户角色
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 9:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user_role", indexes = {@Index(columnList = "role_id")})
@IdClass(UserRoleFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "user_role", comment = "用户角色")
public class UserRole extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserRoleFundPrimarykey userRoleFundPrimarykey;

    private String userId;

    private String roleId;

}
