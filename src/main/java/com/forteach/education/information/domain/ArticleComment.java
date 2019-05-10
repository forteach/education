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

	/** 评论编号. **/
	@Id
	@Column(length = 32, nullable = false, name = "comment_id")
	private String commentId;

	/** 文章编号. **/
	@Column(length = 32, nullable = false, name = "article_id")
	private String articleId;
	
	/** 评论文章用户编号. **/
	@Column(length = 32, nullable = false, name = "user_id")
	private String userId;

	/** 评论文章用户名称.**/
	@Column(length = 40,  name = "user_name")
	private String userName;

	/** 评论文章用户类型（T：教师  S：学生）.**/
	@Column( length = 2,name = "user_type")
	private String userType;

	/** 评论文章用户头像.**/
	@Column(length = 255,  name = "user_tortrait")
	private String userTortrait;

	/** 评论的内容. **/
	@Column(length = 500, name = "content", nullable = false)
	private String content;
	
	/** 回复的内容. **/
	@Column(length = 500, name = "re_content")
	private String reContent;

	/** 回复的时间. **/
	@Column(name = "re_content_time")
	private String reContenTime;

	/** 评论时用户IP. **/
	@Column(length = 15, name = "ip_address")
	private String ipAddress;

//	/** 评论审批状态. （0通过 、1 未通过） **/
//	@Column(name = "ap_status")
//	private int spStatus;
	
 	/**评论点赞数量 **/
	@Column(name="good_count")
	private int goodCount;
	
	/**评论举报数量 **/
	@Column(name="repost_count")
	private int repostCount;
	
//	/**评论楼层**/
//	@Column(name="floor")
//	private int floor;
	
	/**回复人别名**/
	@Column(length=32, name="reply_userNam")
	private String replyUserName;

}