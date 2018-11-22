package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "问题册")
public class QuestionTxt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @ApiModelProperty(value = "问题册主键", hidden = true)
    private QuestionTxtFundPrimarykey questionTxtFundPrimarykey;

    @ApiModelProperty(value = "问题内容ID", name = "txtId", dataType = "string")
    private String txtId;

    @ApiModelProperty(value = "问题册编号", name = "classQuestionId", dataType = "string")
    private String classQuestionId;

    @ApiModelProperty(value = "问题内容", name = "paperInfo", dataType = "string")
    @Column(name = "paper_info", columnDefinition = "text(65535) COMMENT '问题内容'")
    private String paperInfo;


}
