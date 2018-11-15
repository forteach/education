package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:　系统角色
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_role")
@ApiModel(value = "角色对象")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SysRole extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "role_id", columnDefinition = "varchar(255) COMMENT '角色编号'")
    private String roleId;

    @NotNull(message = "请输入角色名称")
    @ApiModelProperty(value = "角色名称", notes = "角色名称", name = "roleName", example = "系统管理员", required = true)
    @Column(name = "role_name", columnDefinition = "varchar(40) COMMENT '角色名称'")
    private String roleName;

    @NotNull(message = "生效标识出错")
    @ApiModelProperty(value = "生效标识", notes = "生效标识 0生效 1失效", name = "isValidated", example = "0", required = true)
    @Column(name = "is_validated", columnDefinition = "char(1) COMMENT '生效标识 0生效 1失效'")
    private String isValidated;

    @Column(name = "remark", columnDefinition = "varchar(255) COMMENT '备注 角色说明'")
    private String remark;

    @Column(name = "u_time", columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date uTime;

    @Column(name = "c_time", columnDefinition = "datetime COMMENT '创建时间'")
    private Date cTime;

}
