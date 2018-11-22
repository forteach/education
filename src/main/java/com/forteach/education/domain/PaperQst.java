package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 试卷选择考题
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 16:14
 */
@Data
@Entity
@Table(name = "paper_qst", indexes = {@Index(columnList = "paper_id"), @Index(columnList = "choice_qst_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(PaperQstFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "paper_qst", comment = "试卷选择考题")
@ApiModel(value = "试卷选择考题")
public class PaperQst extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PaperQstFundPrimarykey paperQstFundPrimarykey;

    @ApiModelProperty(value = "试卷编号", name = "paperId", dataType = "string", required = true)
    private String paperId;

    @ApiModelProperty(value = "试题编号", name = "choiceQstId", dataType = "string",required = true)
    private String choiceQstId;


}
