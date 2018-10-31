package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_role")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SysRole extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
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

    @Column(name = "c_time", columnDefinition = "datetime COMMENT '创建时间'")
    private Date cTime;

}
