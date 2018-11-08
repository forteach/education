package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_users")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SysUsers extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(columnDefinition = "varchar(32) COMMENT '主键 uuid'")
    private String id;

    @Column(name = "pass_word", columnDefinition = "varchar(255) COMMENT '用户密码'")
    private String passWord;

    @Column(name = "user_name", columnDefinition = "varchar(40) COMMENT '用户名称'")
    private String userName;

    @Column(name = "account", columnDefinition = "varchar(40) COMMENT '用户账号'")
    private String account;

    @Column(name = "register_phone", columnDefinition = "varchar(20) COMMENT '注册手机号'")
    private String registerPhone;

    @Column(name = "email", columnDefinition = "varchar(255) COMMENT '邮箱地址'")
    private String email;

    @Column(name = "slat", columnDefinition = "int(11) COMMENT '密码随机值'")
    private Integer slat;

    @Column(name = "open_id", columnDefinition = "varchar(50) COMMENT '设备用 openid'")
    private String openId;

    @Column(name = "sign", columnDefinition = "varchar(32) COMMENT '登陆签名'")
    private String sign;

    @Column(name = "is_lock", columnDefinition = "char(1) COMMENT '锁定标记 （N/Y）'")
    private String isLock;

    @Column(name = "equipment", columnDefinition = "varchar(40) COMMENT '登陆设备'")
    private String equipment;




}
