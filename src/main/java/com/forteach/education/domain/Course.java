package com.forteach.education.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Description: 科目
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 16:42
 */
@Data
@Entity
@Table(name = "course", indexes = {@Index(columnList = "specialty_id"), @Index(columnList = "specialty_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "course", comment = "科目")
public class Course extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    @ApiModelProperty(name = "科目编号", value = "courseId", dataType = "string")
    private String courseId;

    @ApiModelProperty(value = "specialtyId", name = "专业号", dataType = "string")
    @Column(name = "specialty_id", columnDefinition = "VARCHAR(32) COMMENT '专业号'")
    private String specialtyId;

    @ApiModelProperty(value = "courseName", name = "科目名称", dataType = "string")
    @Column(name = "course_name", columnDefinition = "VARCHAR(40) COMMENT '科目名称'")
    private String courseName;

    @Size(min = 1, max = 3, message = "分享类型错误")
    @ApiModelProperty(value = "share_type", name = "shareType", dataType = "int", notes = "分享类型：１.私有 2.协作 ３.公开,默认私有")
    @Column(name = "share_type", columnDefinition = "INT DEFAULT 1 COMMENT '分享类型：１.私有 2.协作 ３.公开'")
    private Integer shareType;

}
