package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:　系统活动或动作
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 9:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_action")
@org.hibernate.annotations.Table(appliesTo = "sys_action", comment = "系统活动或动作")
public class SysAction extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "sys_act_id", columnDefinition = "int(11) COMMENT '动作编号'")
    private String sysActId;

    @Column(name = "sys_act_name", columnDefinition = "varchar(40) COMMENT '动作名称'")
    private String sysActName;

    @Column(name = "is_validated", columnDefinition = "char(1) COMMENT '生效标记'")
    private String isValidated;


}
