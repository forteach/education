package com.forteach.education.paper.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:45
 */
@Data
@Embeddable
public class PaperOpinFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'",insertable = false,updatable = false)
    private String paperId;

    @Column(name = "question_id", columnDefinition = "varchar(32) COMMENT '题型编号'",insertable = false,updatable = false)
    private String questionId;

    public PaperOpinFundPrimarykey(String paperId, String questionId) {
        this.paperId = paperId;
        this.questionId = questionId;
    }

    public PaperOpinFundPrimarykey() {
    }
}
