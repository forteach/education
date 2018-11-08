package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:37
 */
@Data
@Entity
@Table(name = "paper_opin", indexes = {@Index(columnList = "paper_id"), @Index(columnList = "question_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(PaperOpinFundPrimarykey.class)
public class PaperOpin extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PaperOpinFundPrimarykey paperOpinFundPrimarykey;

    private String paperId;

    private String questionId;

}
