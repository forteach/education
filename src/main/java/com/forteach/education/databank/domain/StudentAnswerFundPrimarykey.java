package com.forteach.education.databank.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:43
 */
@Data
@Embeddable
public class StudentAnswerFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "class_question_id", columnDefinition = "varchar(32) COMMENT '问题册编号'", insertable = false, updatable = false)
    private String classQuestionId;

    @Column(name = "examineeId", columnDefinition = "varchar(32) COMMENT '考生id'", insertable = false, updatable = false)
    private String examineeId;

    public StudentAnswerFundPrimarykey() {
    }

    public StudentAnswerFundPrimarykey(String classQuestionId, String examineeId) {
        this.classQuestionId = classQuestionId;
        this.examineeId = examineeId;
    }
}
