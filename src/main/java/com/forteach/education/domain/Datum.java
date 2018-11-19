package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 科目资料资料库 资料类型：1、文档 2、视频 3、音频、4、ppt
 * 资料领域：1、预习参考2、案例参考3、知识点材料 4、复习参考
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 16:56
 */
@Data
@Entity
@Table(name = "datum", indexes = {@Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "datum", comment = "科目资料资料库")
public class Datum extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "datum_id", columnDefinition = "varchar(32) COMMENT '资料编号'")
    private String datumId;

    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @Column(name = "datum_name", columnDefinition = "varchar(60) COMMENT '资料名称'")
    private String datumName;

    @Column(name = "datum_type", columnDefinition = "int(11) COMMENT '资料类型'")
    private String datumType;

    @Column(name = "datum_area", columnDefinition = "int(11) COMMENT '资料领域'")
    private String datumArea;

    @Column(name = "datum_url", columnDefinition = "varchar(255) COMMENT '资料路径'")
    private String datumUrl;

    @Column(name = "remark", columnDefinition = "varchar(255) COMMENT '备注说明'")
    private String remark;




}
