package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　试卷评分
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 10:27
 */
@Data
@Entity
@Table(name = "paper_scorer", indexes = {@Index(columnList = "paper_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "paper_scorer", comment = "试卷评分")
@ApiModel(value = "试卷评分")
public class PaperScorer extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "scorer_id", columnDefinition = "varchar(32) COMMENT '评分人ID'")
    @ApiModelProperty(value = "评分人ID", name = "acorerId", dataType = "string")
    private String acorerId;

    @ApiModelProperty(value = "试卷编号", name = "paperId", dataType = "string")
    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'")
    private String paperId;

    @ApiModelProperty(value = "评分人编号", name = "scorerCode", dataType = "string")
    @Column(name = "scorer_code", columnDefinition = "varchar(32) COMMENT '评分人编号'")
    private String scorerCode;

    @ApiModelProperty(value = "评分人姓名", name = "scorerName", dataType = "string")
    @Column(name = "scorer_name", columnDefinition = "varchar(60) COMMENT '评分人姓名'")
    private String scorerName;

    @ApiModelProperty(value = "scorerPwd", name = "scorerPwd", dataType = "string")
    @Column(name = "scorer_pwd", columnDefinition = "varchar(20) COMMENT '评分人密码'")
    private String scorerPwd;
}
