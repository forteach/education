package com.forteach.education.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:30
 */
@Data
@Embeddable
public class PaperTxtFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "txt_id", columnDefinition = "varchar(32) COMMENT '试卷内容ID'", insertable = false, updatable = false)
    private String txtId;

    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'", insertable = false, updatable = false)
    private String paperId;

    public PaperTxtFundPrimarykey() {
    }

    public PaperTxtFundPrimarykey(String txtId, String paperId) {
        this.txtId = txtId;
        this.paperId = paperId;
    }
}
