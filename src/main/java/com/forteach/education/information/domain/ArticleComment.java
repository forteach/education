package com.forteach.education.information.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/***
 *
 * @author zjw 文章评论
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "article_comment")
@org.hibernate.annotations.Table(appliesTo = "article_comment", comment = "文章评论")
@AllArgsConstructor
@NoArgsConstructor
public class ArticleComment extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论编号.
     **/
    @Id
    @Column(nullable = false, name = "comment_id", columnDefinition = "VARCHAR(32) COMMENT '评论编号'")
    private String commentId;

    /**
     * 文章编号.
     **/
    @Column(name = "article_id", columnDefinition = "VARCHAR(32) COMMENT '文章编号'")
    private String articleId;

    /**
     * 评论文章用户编号.
     **/
    @Column(name = "user_id", columnDefinition = "VARCHAR(32) COMMENT '评论文章用户编号'")
    private String userId;

    /**
     * 评论文章用户名称.
     **/
    @Column(name = "user_name", columnDefinition = "VARCHAR(32) COMMENT '评论文章用户名称'")
    private String userName;

    /**
     * 评论文章用户类型（T：教师  S：学生）.
     **/
    @Column(length = 2, name = "user_type", columnDefinition = "VARCHAR(2) COMMENT '评论文章用户类型(T：教师  S：学生)'")
    private String userType;

    /**
     * 评论文章用户头像.
     **/
    @Column(name = "user_tortrait", columnDefinition = "VARCHAR(32) COMMENT '评论文章的用户头像'")
    private String userTortrait;

    /**
     * 评论的内容.
     **/
    @Column(name = "content", columnDefinition = "VARCHAR(10000)　COMMENT '评论的内容'")
    private String content;

    /**
     * 回复的内容.
     **/
    @Column(name = "re_content", columnDefinition = "VARCHAR(2000) COMMENT '回复的内容'")
    private String reContent;

    /**
     * 回复的时间.
     **/
    @Column(name = "re_content_time", columnDefinition = "VARCHAR(32) COMMENT '回复的时间'")
    private String reContenTime;

    /**
     * 评论时用户IP.
     **/
    @Column(name = "ip_address", columnDefinition = "VARCHAR(32) COMMENT '评论用户的id'")
    private String ipAddress;

//	/** 评论审批状态. （0通过 、1 未通过） **/
//	@Column(name = "ap_status")
//	private int spStatus;

    /**
     * 评论点赞数量
     */
    @Column(name = "good_count", columnDefinition = "VARCHAR(32) COMMENT '评论点赞数量'")
    private int goodCount;

    /**
     * 评论举报数量
     **/
    @Column(name = "repost_count", columnDefinition = "VARCHAR(32) COMMENT '评论举报数量'")
    private int repostCount;

//	/**评论楼层**/
//	@Column(name="floor")
//	private int floor;

    /**
     * 回复人别名
     */
    @Column(length = 32, name = "reply_userNam", columnDefinition = "VARCHAR(32) COMMENT '回复人别名'")
    private String replyUserName;

}