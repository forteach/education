package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "user_attention", indexes = {@Index(columnList = "att_id"), @Index(columnList = "article_id")})
@org.hibernate.annotations.Table(appliesTo = "user_attention", comment = "用户关注的文章")
@ApiModel(value = "用户关注的文章")
public class UserAttention extends Entitys implements Serializable {

    @Id
    @Column(name = "att_id", columnDefinition = "varchar(32) COMMENT '关注ID'")
    @ApiModelProperty(value = "关注ID", name = "attId", dataType = "string")
    private String attId;

    @Column(name = "article_id", columnDefinition = "varchar(32) COMMENT '文章编号'")
    @ApiModelProperty(value = "文章编号", name = "articleId", dataType = "string", required = true)
    private String articleId;

    public UserAttention(String attId, String articleId) {
        this.attId = attId;
        this.articleId = articleId;
    }

    public UserAttention() {
    }
}
