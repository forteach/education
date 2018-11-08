package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "role_col_act", indexes = {@Index(columnList = "sys_act_id"), @Index(columnList = "role_id")})
@IdClass(RoleColActFundPrimarykey.class)
public class RoleColAct extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoleColActFundPrimarykey roleColActFundPrimarykey;

    private String colId;

    private String sysActId;

    private String roleId;

}

