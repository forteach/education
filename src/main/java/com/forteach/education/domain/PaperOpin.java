package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　试卷判断题
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:37
 */
@Data
@Entity
@Table(name = "paper_opin", indexes = {@Index(columnList = "paper_id"), @Index(columnList = "question_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(PaperOpinFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "paper_opin", comment = "试卷判断题")
@ApiModel(value = "试卷判断题")
@AllArgsConstructor
@NoArgsConstructor
public class PaperOpin extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @ApiModelProperty(value = "试卷判断题主键", hidden = true)
    private PaperOpinFundPrimarykey paperOpinFundPrimarykey;

    @ApiModelProperty(name = "paperId", value = "试卷编号", required = true, dataType = "string")
    private String paperId;

    @ApiModelProperty(value = "题型编号", name = "questionId", required = true, dataType = "string")
    private String questionId;

}
