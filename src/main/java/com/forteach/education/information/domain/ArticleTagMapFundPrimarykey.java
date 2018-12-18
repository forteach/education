package com.forteach.education.information.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 16:37
 */
@Data
@Embeddable
public class ArticleTagMapFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "article_id", columnDefinition = "varchar(32) COMMENT '文章编号'", insertable = false, updatable = false)
    private String articleId;

    @Column(name = "tagId", columnDefinition = "varchar(32) COMMENT '标签编号'", insertable = false, updatable = false)
    private String tagId;

    public ArticleTagMapFundPrimarykey() {
    }

    public ArticleTagMapFundPrimarykey(String articleId, String tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
