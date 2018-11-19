package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 考生答题卡
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:06
 */
@Data
@Entity
@Table(name = "examinee_answer", indexes = {@Index(columnList = "paper_id"), @Index(columnList = "examinee_id")})
@IdClass(ExamineeAnswerFundPrimarykey.class)
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "examinee_answer", comment = "考生答题卡")
public class ExamineeAnswer extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ExamineeAnswerFundPrimarykey questionTxtFundPrimarykey;

    private String paperId;

    private String examineeId;

    @Column(name = "answer_id", columnDefinition = "varchar(32) COMMENT '作答id'")
    private String answerId;

    @Column(name = "answer_content", columnDefinition = "varchar(5000) COMMENT '答案'")
    private String answerContent;

}
