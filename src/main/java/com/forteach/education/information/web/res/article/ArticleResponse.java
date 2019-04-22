package com.forteach.education.information.web.res.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文章资讯输出
 * 
 * @author
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse implements Serializable {

	/** 文章编号.**/
	private String articleId;

	/** 课程编号.**/
	private String courseId;

	/** 发布人编号.**/
	private String userId;

	/** 班级编号.**/
	private String classId;

	/** 文章题目.**/
	private String title;

	// 图片连接.**/
	private String imgUrl;

	// 文章连接.**/
	private String linkUrl;

	/** 文章描述.**/
	private String description;

	/** 文章内容.**/
	private String articleConten;

	/** 资讯分类. **/
	private String articleType;

}
