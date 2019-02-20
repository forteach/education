package com.forteach.education.paper.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:07
 */
@Data
@Embeddable
public class ExamineeAnswerFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'", insertable = false, updatable = false)
    private String paperId;

    @Column(name = "examinee_id", columnDefinition = "varchar(32) COMMENT '考生id'", insertable = false, updatable = false)
    private String examineeId;

    public ExamineeAnswerFundPrimarykey() {
    }

    public ExamineeAnswerFundPrimarykey(String paperId, String examineeId) {
        this.paperId = paperId;
        this.examineeId = examineeId;
    }
}
