package com.forteach.education.authority.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:　系统角色
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "sys_role", indexes = {@Index(name = "role_id_index", columnList = "role_id")})
@ApiModel(value = "角色对象")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@org.hibernate.annotations.Table(appliesTo = "sys_role", comment = "系统角色")
public class SysRole extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "role_id", columnDefinition = "VARCHAR(255) COMMENT '角色编号'")
    private String roleId;

    @NotNull(message = "请输入角色名称")
    @ApiModelProperty(value = "角色名称", notes = "角色名称", name = "roleName", example = "系统管理员", required = true)
    @Column(name = "role_name", columnDefinition = "VARCHAR(40) COMMENT '角色名称'")
    private String roleName;

    @ApiModelProperty(value = "生效标识", notes = "生效标识 0生效 1失效", name = "isValidated", example = "0", required = true)
    @Column(name = "is_validated", columnDefinition = "CHAR(1) COMMENT '生效标识 0生效 1失效'")
    private String isValidated;

    @Column(name = "remark", columnDefinition = "VARCHAR(255) COMMENT '备注 角色说明'")
    private String remark;

}
