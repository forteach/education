package com.forteach.education.domain;

/**
 * @Description: 试卷简答思考题
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:52
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "paper_design", indexes = {@Index(columnList = "paper_id"), @Index(columnList = "design_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(PaperDesignFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "paper_design", comment = "试卷简答思考题")
public class PaperDesign extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PaperDesignFundPrimarykey paperDesignFundPrimarykey;

    private String paperId;

    private String designId;


}
