package com.forteach.education.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:33
 */
@Data
@Entity
@Table(name = "sys_role")
public class SysRole extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "role_id", columnDefinition = "varchar(255) COMMENT '角色编号'")
    private String roleId;

    @Column(name = "role_name", columnDefinition = "varchar(40) COMMENT '角色名称'")
    private String roleName;

    @Column(name = "is_validated", columnDefinition = "char(1) COMMENT '生效标识 0生效 1失效'")
    private String isValidated;

    @Column(name = "remark", columnDefinition = "varchar(255) COMMENT '备注 角色说明'")
    private String remark;

    @Column(name = "u_time", columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date uTime;

    @Column(name = "c_time", columnDefinition = "timestamp COMMENT '创建时间'")
    private Date cTime;

}
