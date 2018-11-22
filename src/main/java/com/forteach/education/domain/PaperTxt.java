package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　试卷内容
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:29
 */
@Data
@Entity
@Table(name = "paper_txt", indexes = {@Index(columnList = "paper_id")})
@IdClass(PaperTxtFundPrimarykey.class)
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "paper_txt", comment = "试卷内容")
@ApiModel(value = "试卷内容")
public class PaperTxt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @ApiModelProperty(value = "试卷内容主键", hidden = true)
    private PaperTxtFundPrimarykey paperTxtFundPrimarykey;

    @ApiModelProperty(value = "试卷内容ID", name = "txtId", dataType = "string")
    private String txtId;

    @ApiModelProperty(value = "试卷编号", name = "paperId", dataType = "string")
    private String paperId;

    @ApiModelProperty(value = "试卷内容", name = "paperInfo", dataType = "string")
    @Column(name = "paper_info", columnDefinition = "text(65535) COMMENT '试卷内容'")
    private String paperInfo;


}
