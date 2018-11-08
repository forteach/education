package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 17:15
 */
@Data
@Entity
@Table(name = "choice_qst", indexes = {@Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
public class ChoiceQst extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "choice_qst_id", columnDefinition = "varchar(32) COMMENT '试题编号'")
    private String choiceQstId;

    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @Column(name = "choice_qst_txt", columnDefinition = "varchar(500) COMMENT '试题内容'")
    private String choiceQstTxt;

    @Column(name = "choice_qst_answ", columnDefinition = "varchar(20) COMMENT '答案'")
    private String choiceQstAnsw;

    @Column(name = "choice_type", columnDefinition = "varchar(10) COMMENT '选择题类型'")
    private String choiceType;










}
