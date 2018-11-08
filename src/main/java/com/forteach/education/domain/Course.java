package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 16:42
 */
@Data
@Entity
@Table(name = "course", indexes = {@Index(columnList = "specialty_id")})
@EqualsAndHashCode(callSuper = true)
public class Course extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @Column(name = "specialty_id", columnDefinition = "varchar(32) COMMENT '专业号'")
    private String specialtyId;

    @Column(name = "course_name", columnDefinition = "varchar(40) COMMENT '科目名称'")
    private String courseName;

}
