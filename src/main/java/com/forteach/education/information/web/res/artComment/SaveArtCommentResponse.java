package com.forteach.education.information.web.res.artComment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 创建资讯评论
 * 
 * @author zjw
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveArtCommentResponse {

	/** 评论编号. **/
	private String commentId;

	/** 文章编号. **/
	private String articleId;

	/** 评论文章用户编号. **/
	private String userId;

	/** 评论文章用户名称.**/
	private String userName;

	/** 评论文章用户头像.**/
	private String userTortrait;

	/** 评论的内容. **/
	private String content;

	/** 回复的内容. **/
	private String reContent;

	/** 回复的时间. **/
	private String reContenTime;

	/**评论点赞数量 **/
	private int goodCount;

	//评论人员类型 S 学生  T 教师
	private String userType;

	/**评论点赞数量 **/
	private String replyUserName;

}