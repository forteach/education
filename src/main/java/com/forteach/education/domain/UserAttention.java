package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@org.hibernate.annotations.Table(appliesTo = "user_attention", comment = "用户关注的文章")
@ApiModel(value = "用户关注的文章")
@AllArgsConstructor
@NoArgsConstructor
public class UserAttention extends Entitys implements Serializable {

    @EmbeddedId
    @ApiModelProperty(value = "用户关注的文章主键", hidden = true)
    private UserAttentionFundPrimaryKey userAttentionFundPrimaryKey;

    @ApiModelProperty(value = "关注ID", name = "attId", dataType = "string")
    private String attId;

    @ApiModelProperty(value = "文章编号", name = "articleId", dataType = "string", required = true)
    private String articleId;
}
