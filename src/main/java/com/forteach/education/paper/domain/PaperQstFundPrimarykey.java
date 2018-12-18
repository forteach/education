package com.forteach.education.paper.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 16:57
 */
@Data
@Embeddable
public class PaperQstFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'",insertable = false,updatable = false)
    private String paperId;

    @Column(name = "choice_qst_id", columnDefinition = "varchar(32) COMMENT '试题编号'",insertable = false,updatable = false)
    private String choiceQstId;

    public PaperQstFundPrimarykey() {
    }

    public PaperQstFundPrimarykey(String paperId, String choiceQstId) {
        this.paperId = paperId;
        this.choiceQstId = choiceQstId;
    }
}
