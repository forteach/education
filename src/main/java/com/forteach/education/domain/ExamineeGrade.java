package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 10:35
 */
@Data
@Entity
@Table(name = "examinee_grade", indexes = {@Index(columnList = "paper_id"), @Index(columnList = "examinee_id")})
@EqualsAndHashCode(callSuper = true)
public class ExamineeGrade extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "grade_id", columnDefinition = "varchar(32) COMMENT '成绩编号'")
    private String gradeId;

    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'")
    private String paperId;

    @Column(name = "examinee_id", columnDefinition = "varchar(32) COMMENT '考生ID'")
    private String examineeId;

    @Column(name = "grade_value", columnDefinition = "double(12,5) COMMENT '成绩'")
    private Double gradeValue;


}
