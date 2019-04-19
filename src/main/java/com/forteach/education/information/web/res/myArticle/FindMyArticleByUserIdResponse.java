package com.forteach.education.information.web.res.myArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//查询我收藏的知识库文章
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindMyArticleByUserIdResponse implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	// 编号
	private String articleId;
	// 发布者编号
	private String postUserId;
	// 模块编号
	private String modId;
	// 文章题目
	private String title;
	// 文章作者
	private String author;
	// 关键字
	private String keyWord;
	// 文件作者的email
	private String authorEmail;
	// 文章类型.
	private String articType;
	// 文章添加时间
	private String addTime;
	// 图片连接
	private String imgUrl;
	// 点击量
	private int clickCount;

	// 收藏量
	private int collectCount;
	// 是否生效
	private String isValidated;
	// 是否显示
	private String isShow;
	// 描述
	private String description;

}
