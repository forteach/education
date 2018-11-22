package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　用户评论
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 16:41
 */
@Data
@Entity
@Table(name = "user_comment", indexes = {@Index(columnList = "comment_id"), @Index(columnList = "article_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "user_comment", comment = "用户评论")
@ApiModel(value = "用户评论")
public class UserComment extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "comment_id", columnDefinition = "varchar(32) COMMENT '评论id'")
    @ApiModelProperty(value = "评论id", name = "commentId", dataType = "string")
    private String commentId;

    @ApiModelProperty(value = "文章编号", name = "articleId", dataType = "string", required = true)
    @Column(name = "article_id", columnDefinition = "varchar(32) COMMENT '文章编号'")
    private String articleId;

    @ApiModelProperty(value = "评论内容", name = "commitContent", dataType = "string", required = true)
    @Column(name = "commit_content", columnDefinition = "varchar(255) COMMENT '评论内容'")
    private String commitContent;

    @ApiModelProperty(value = "评论者", name = "commitUser", dataType = "string", required = true)
    @Column(name = "commit_user", columnDefinition = "varchar(32) COMMENT '评论者'")
    private String commitUser;

}
