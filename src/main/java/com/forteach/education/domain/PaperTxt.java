package com.forteach.education.domain;

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
public class PaperTxt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PaperTxtFundPrimarykey paperTxtFundPrimarykey;

    private String txtId;

    private String paperId;

    @Column(name = "paper_info", columnDefinition = "text(65535) COMMENT '试卷内容'")
    private String paperInfo;


}
