package com.forteach.education.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-8 15:51
 * @Version: 1.0
 * @Description: 用户关注的文章的复合主键
 */
@Data
@Embeddable
public class UserAttentionFundPrimaryKey implements Serializable {
    @Column(name = "att_id", columnDefinition = "varchar(32) COMMENT '关注ID'", insertable = false, updatable = false)
    private String attId;

    @Column(name = "article_id", columnDefinition = "varchar(32) COMMENT '文章编号'", insertable = false, updatable = false)
    private String articleId;

    public UserAttentionFundPrimaryKey() {
    }

    public UserAttentionFundPrimaryKey(String attId, String articleId) {
        this.attId = attId;
        this.articleId = articleId;
    }
}
