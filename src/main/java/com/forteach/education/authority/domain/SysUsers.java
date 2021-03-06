package com.forteach.education.authority.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * @Description:　系统用户
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:51
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@DynamicInsert
@ApiModel(value = "管理员信息", description = "管理员信息")
@Table(name = "sys_users", indexes = {@Index(name = "id_index", columnList = "id")})
@org.hibernate.annotations.Table(appliesTo = "sys_users", comment = "系统用户")
public class SysUsers extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(columnDefinition = "varchar(32) COMMENT '主键 uuid'")
    private String id;

    @ApiModelProperty(hidden = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "pass_word", columnDefinition = "varchar(255) COMMENT '用户密码'")
    private String passWord;

    @Column(name = "user_name", columnDefinition = "varchar(40) COMMENT '用户名称'")
    private String userName;

    @Column(name = "account", unique = true, columnDefinition = "varchar(40) COMMENT '用户账号'")
    private String account;

    @ApiModelProperty(name = "registerPhone", value = "注册手机号", dataType = "string")
    @Column(name = "register_phone", unique = true, columnDefinition = "varchar(20) COMMENT '注册手机号'")
    private String registerPhone;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(name = "email", value = "邮箱", dataType = "string")
    @Column(name = "email", columnDefinition = "varchar(255) COMMENT '邮箱地址'")
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(hidden = true)
    @Column(name = "slat", columnDefinition = "int(11) COMMENT '密码随机值'")
    private Integer slat;

    @ApiModelProperty(name = "openId", value = "设备OPNEID", dataType = "string")
    @Column(name = "open_id", columnDefinition = "varchar(50) COMMENT '设备用 openid'")
    private String openId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ApiModelProperty(hidden = true)
    @Column(name = "sign", columnDefinition = "varchar(32) COMMENT '登陆签名'")
    private String sign;

    @Column(name = "is_lock", columnDefinition = "char(1) COMMENT '锁定标记 (N/Y)'")
    @ApiModelProperty(name = "isLock", value = "是否锁定", dataType = "string", notes = "锁定标记 (Y/N)")
    private String isLock;

    @Column(name = "equipment", columnDefinition = "varchar(40) COMMENT '登陆设备'")
    @ApiModelProperty(name = "equipment", value = "登陆设备", dataType = "string")
    private String equipment;

    @ApiModelProperty(hidden = true)
    @Column(name = "teacher_id", columnDefinition = "varchar(32) comment '教师id'")
    private String teacherId;

    @Column(name = "teacher_office_id", columnDefinition = "varchar(32) comment '教研室id'")
    private String teacherOfficeId;

    @Column(name = "teacher_office_name", columnDefinition = "varchar(32) comment '教研室名称'")
    private String teacherOfficeName;

    /**
     * 角色id
     */
    @Transient
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String role;

    /**
     * 记住我
     */
    @Transient
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer rememberMe;


}
