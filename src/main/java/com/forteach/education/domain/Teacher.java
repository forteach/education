package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 14:11
 */
@Data
@Entity
@Table(name = "teacher",indexes = {@Index(columnList = "specialty_id")})
@EqualsAndHashCode(callSuper = true)
public class Teacher extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "teacher_id", nullable = false, columnDefinition = "varchar(32) COMMENT '老师id uuid'")
    private String teacherId;

    @Column(name = "specialty_id", columnDefinition = "varchar(32) COMMENT '专业号'")
    private String specialtyId;

    @Column(name = "teacher_name", columnDefinition = "varchar(40) COMMENT '教师名称'")
    private String teacherName;

    @Column(name = "teacher_code", columnDefinition = "varchar(32) COMMENT '教师编号'")
    private String teacherCode;


}
