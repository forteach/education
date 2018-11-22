package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: (专业)
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:01
 */
@Data
@Entity
@Table(name = "specialty", indexes = {@Index(columnList = "specialty_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "specialty", comment = "专业")
@ApiModel(value = "专业信息", description = "专业信息")
public class Specialty extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "specialty_id", columnDefinition = "varchar(32) COMMENT '专业号'")
    @ApiModelProperty(value = "专业ID", name = "specialtyId", dataType = "string")
    private String specialtyId;

    @ApiModelProperty(value = "专业名称", name = "specialtyName", required = true, dataType = "string")
    @Column(name = "specialty_name", columnDefinition = "varchar(255) COMMENT '专业名称'")
    private String specialtyName;


}
