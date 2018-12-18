package com.forteach.education.databank.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description: 问题册内容
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:18
 */
@Data
@Embeddable
public class QuestionTxtFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "txt_id", columnDefinition = "varchar(32) COMMENT '问题内容ID'",insertable = false,updatable = false)
    private String txtId;

    @Column(name = "class_question_id", columnDefinition = "varchar(32) COMMENT '问题册编号'",insertable = false,updatable = false)
    private String classQuestionId;

    public QuestionTxtFundPrimarykey() {
    }

    public QuestionTxtFundPrimarykey(String txtId, String classQuestionId) {
        this.txtId = txtId;
        this.classQuestionId = classQuestionId;
    }
}
