package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: 考生信息
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:51
 */
@Data
@Entity
@Table(name = "examinee")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "examinee", comment = "考生信息")
public class Examinee extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "examinee_id", columnDefinition = "varchar(32) COMMENT '考生id'")
    private String examineeId;

    @Column(name = "examinee_code", columnDefinition = "varchar(32) COMMENT '考生编号'")
    private String examineeCode;

    @Column(name = "examinee_name", columnDefinition = "varchar(32) COMMENT '考生姓名'")
    private String examineeName;

    @Column(name = "examinee_pwd", columnDefinition = "varchar(32) COMMENT '登陆密码'")
    private String examineePwd;


}
