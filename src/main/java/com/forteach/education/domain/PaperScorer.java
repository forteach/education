package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class PaperScorer extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "scorer_id", columnDefinition = "varchar(32) COMMENT '评分人ID'")
    private String acorerId;

    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'")
    private String paperId;

    @Column(name = "scorer_code", columnDefinition = "varchar(32) COMMENT '评分人编号'")
    private String scorerCode;

    @Column(name = "scorer_name", columnDefinition = "varchar(60) COMMENT '评分人姓名'")
    private String scorerName;

    @Column(name = "scorer_pwd", columnDefinition = "varchar(20) COMMENT '评分人密码'")
    private String scorerPwd;
}
