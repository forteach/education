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
public class ArticleStuListResponse implements IArtTag,Serializable {

	/** 文章编号.**/
	private String articleId;

	/** 课程编号.**/
	private String courseId;

	private String courseName;

	/** 文章题目.**/
	private String title;

	// 图片连接.**/
	private String imgUrl;

	/** 文章描述.**/
	private String description;

	/** 资讯分类. **/
	private String articleType;

	/** 收藏数量 **/
	private int collectCount;

	/** 点赞数量 **/
	private int clickGood;

	/** 评论数量 **/
	private int commentCount;

//学生端功能属性
	/** 是否精华 */
	private String isNice;

	/** 是否收藏 */
	private String isCollect;

	/** 是否点赞 */
	private String isClickGood;

	/** 是否是本人贴 */
	private String isMy;


//发布学生********************************
	/** 发布人编号.**/
	private String userId;

	/** 发布人名称.**/
	private String userName;

	/** 发布人头像.**/
	private String userTortrait;

//班级********************************
	/** 班级编号.**/
	private String classId;

	/** 班级名称.**/
	private String className;

	public String createTime;

}
