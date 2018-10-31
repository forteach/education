package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name = "u_time", columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date uTime;

    @Column(name = "c_time", columnDefinition = "datetime COMMENT '创建时间'")
    private Date cTime;
}

