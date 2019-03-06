package com.forteach.education.paper.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Table(name = "examinee_answer", indexes = {@Index(columnList = "paper_id", name = "paper_id_index"), @Index(columnList = "examinee_id", name = "examinee_id_index")})
@IdClass(ExamineeAnswerFundPrimarykey.class)
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "examinee_answer", comment = "考生答题卡")
@ApiModel(value = "考生答题卡")
@AllArgsConstructor
@NoArgsConstructor
public class ExamineeAnswer extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @ApiModelProperty(value = "考生答题卡主键", hidden = true)
    private ExamineeAnswerFundPrimarykey questionTxtFundPrimarykey;

    @ApiModelProperty(value = "试卷编号", name = "paperId", dataType = "string")
    private String paperId;

    @ApiModelProperty(value = "考生id", name = "examineeId", dataType = "string")
    private String examineeId;

    @ApiModelProperty(value = "作答id", name = "answerId", dataType = "string")
    @Column(name = "answer_id", columnDefinition = "varchar(32) COMMENT '作答id'")
    private String answerId;

    @ApiModelProperty(value = "答案", name = "answerContent", dataType = "string")
    @Column(name = "answer_content", columnDefinition = "varchar(5000) COMMENT '答案'")
    private String answerContent;

}
