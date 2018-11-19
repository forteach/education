package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　问题册
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:13
 */
@Data
@Entity
@Table(name = "question_txt", indexes = {@Index(columnList = "class_question_id")})
@IdClass(QuestionTxtFundPrimarykey.class)
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "question_txt", comment = "问题册")
public class QuestionTxt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private QuestionTxtFundPrimarykey questionTxtFundPrimarykey;

    private String txtId;

    private String classQuestionId;

    @Column(name = "paper_info", columnDefinition = "text(65535) COMMENT '问题内容'")
    private String paperInfo;


}
