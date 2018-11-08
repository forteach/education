package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-8 15:32
 * @Version: 1.0
 * @Description: 用户关注的文章
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_attention", indexes = {@Index(columnList = "att_id"), @Index(columnList = "article_id")})
@IdClass(UserAttentionFundPrimaryKey.class)
public class UserAttention extends Entitys implements Serializable {

    @EmbeddedId
    private UserAttentionFundPrimaryKey userAttentionFundPrimaryKey;

    private String attId;

    private String articleId;
}
