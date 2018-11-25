package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@ApiModel(value = "考生信息")
@AllArgsConstructor
@NoArgsConstructor
public class Examinee extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "examinee_id", columnDefinition = "varchar(32) COMMENT '考生id'")
    @ApiModelProperty(value = "考生id", name = "examineeId", dataType = "string")
    private String examineeId;

    @ApiModelProperty(value = "考生编号", name = "examineeCode", dataType = "string")
    @Column(name = "examinee_code", columnDefinition = "varchar(32) COMMENT '考生编号'")
    private String examineeCode;

    @ApiModelProperty(value = "考生姓名", name = "examineeName", dataType = "string")
    @Column(name = "examinee_name", columnDefinition = "varchar(32) COMMENT '考生姓名'")
    private String examineeName;

    @ApiModelProperty(value = "登陆密码", name = "examineePwd", dataType = "string")
    @Column(name = "examinee_pwd", columnDefinition = "varchar(32) COMMENT '登陆密码'")
    private String examineePwd;


}
