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

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	// 编号
	private String articleId;

	//发布者编号
	private String userId;

	// 文章题目
	private String title;

	// 文章作者
	private String author;
	
	private String modId;

	private String keyWord;

	/** 文章类型. **/
	private String articType;

	// 文章添加时间
	private String addTime;

	// 图片连接
	private String imgUrl;

	// 点击量
	private int clickCount;

	// 是否生效
	private String isValidated;
	
	//是否显示
	private String isShow;

	private String description;


}
