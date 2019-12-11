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
public class ArticleResponse implements IArtTag, Serializable {

	/** 文章编号.**/
	private String articleId;

	/** 课程编号.**/
	private String courseId;

	/** 课程编号.**/
	private String courseName;

	/** 发布人编号.**/
	private String userId;

	/** 发布人名称.**/
	private String userName;

	/** 发布人头像.**/
	private String userTortrait;

	/** 班级编号.**/
	private String classId;

	/** 班级名称.**/
	private String className;

	/** 文章题目.**/
	private String title;

	/** 外资料引用连接** /
	 private String imgUrl;

	 /** 外资料引用连接**/
	private String linkUrl;

	//@Transient
	/** 是否置顶.**/
	private String isTop;

	/** 文章描述.**/
	private String description;

	/** 文章内容.**/
	private String articleConten;

	/** 点击量.**/
	private int clickCount;

	/** 收藏数量 **/
	private int collectCount;

	/** 点赞数量 **/
	private int clickGood;

	/** 评论数量 **/
	private int commentCount;

	/** 资讯分类. **/
	private String articleType;


	//学生端功能属性
	//@Transient
	/** 是否精华.**/
	private String isNice;

	/** 是否收藏 */
	private String isCollect;

	/** 是否点赞 */
	private String isClickGood;

	/** 是否是本人贴 */
	private String isMy;
}