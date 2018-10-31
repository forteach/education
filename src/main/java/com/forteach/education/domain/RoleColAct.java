package com.forteach.education.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:49
 */
@Data
@Entity
@Table(name = "role_col_act",indexes = {@Index(columnList = "sys_act_id"),@Index(columnList = "role_id")})
public class RoleColAct extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoleColActFundPrimarykey roleColActFundPrimarykey;

    @Column(name = "u_time",columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date uTime;

    @Column(name = "c_time",columnDefinition = "timestamp COMMENT '创建时间'")
    private Date cTime;
}

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:56
 */
@Data
@Embeddable
class RoleColActFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "col_id",columnDefinition = "varchar(32) COMMENT '栏目编号'")
    private String colId;

    @Column(name = "sys_act_id",columnDefinition = "int(11) COMMENT '动作编号'")
    private String sysActId;

    @Column(name = "role_id",columnDefinition = "varchar(32) COMMENT '角色编号'")
    private String roleId;

    public RoleColActFundPrimarykey() {
    }

    public RoleColActFundPrimarykey(String colId, String sysActId, String roleId) {
        this.colId = colId;
        this.sysActId = sysActId;
        this.roleId = roleId;
    }
}
