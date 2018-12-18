package com.forteach.education.authority.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:56
 */
@Data
@Embeddable
public class RoleColActFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "col_id", columnDefinition = "varchar(32) COMMENT '栏目编号'",insertable = false,updatable = false)
    private String colId;

    @Column(name = "sys_act_id", columnDefinition = "int(11) COMMENT '动作编号'",insertable = false,updatable = false)
    private String sysActId;

    @Column(name = "role_id", columnDefinition = "varchar(32) COMMENT '角色编号'",insertable = false,updatable = false)
    private String roleId;

    public RoleColActFundPrimarykey() {
    }

    public RoleColActFundPrimarykey(String colId, String sysActId, String roleId) {
        this.colId = colId;
        this.sysActId = sysActId;
        this.roleId = roleId;
    }
}
