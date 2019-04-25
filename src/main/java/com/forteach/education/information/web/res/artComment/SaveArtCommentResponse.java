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

}