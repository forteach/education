package com.forteach.education.paper.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 14:01
 */
@Data
@Embeddable
public class PaperDesignFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'",insertable = false,updatable = false)
    private String paperId;

    @Column(name = "design_id", columnDefinition = "varchar(32) COMMENT '试题编号'",insertable = false,updatable = false)
    private String designId;

    public PaperDesignFundPrimarykey() {
    }

    public PaperDesignFundPrimarykey(String paperId, String designId) {
        this.paperId = paperId;
        this.designId = designId;
    }
}
