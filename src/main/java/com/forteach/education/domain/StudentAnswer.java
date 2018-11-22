package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　学生答题的答案
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:25
 */
@Data
@Entity
@Table(name = "student_answer", indexes = {@Index(columnList = "class_question_id"),@Index(columnList = "txt_id")})
@IdClass(StudentAnswerFundPrimarykey.class)
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "student_answer", comment = "学生答题的答案")
@ApiModel(value = "学生答题的答案")
public class StudentAnswer extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @ApiModelProperty(value = "学生答题卡主键", hidden = true)
    private StudentAnswerFundPrimarykey studentAnswerFundPrimarykey;

    @ApiModelProperty(value = "问题册ID", name = "classQuestionId", dataType = "string")
    private String classQuestionId;

    @ApiModelProperty(name = "examineeId", value = "考生id", dataType = "string", required = true)
    private String examineeId;

    @ApiModelProperty(value = "考题内容", name = "txtId", dataType = "string", required = true)
    @Column(name = "txt_id", columnDefinition = "varchar(32) COMMENT '考题内容'")
    private String txtId;

    @ApiModelProperty(value = "回答内容", name = "replyContent", dataType = "string")
    @Column(name = "reply_content", columnDefinition = "varchar(5000) COMMENT '回答内容'")
    private String replyContent;

    @ApiModelProperty(value = "正确答案", name = "correctAnswer", dataType = "string")
    @Column(name = "correct_answer", columnDefinition = "varchar(5000) COMMENT '正确答案'")
    private String correctAnswer;

    @ApiModelProperty(value = "参与方式", name = "wayParticipation", dataType = "string")
    @Column(name = "way_participation", columnDefinition = "varchar(32) COMMENT '参与方式'")
    private String wayParticipation;

    @ApiModelProperty(value = "答题结果", name = "answerResults", dataType = "string")
    @Column(name = "answer_results", columnDefinition = "bit COMMENT '答题结果'")
    private boolean answerResults;

    @ApiModelProperty(value = "teacherId", name = "teacherId", dataType = "string")
    @Column(name = "teacher_id", columnDefinition = "varchar(32) COMMENT '批改教师'")
    private String teacherId;



}
