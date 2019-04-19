package com.forteach.education.information.web.res.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindArticleByExpertIdResponse implements Serializable {
	/** 资讯编号. **/
	private String articleId;
	
	/** 资讯分类. **/
	private String catId;
	
	/** 资讯标题. **/
	private String title;
	
	/** 资讯描述. **/
	private String description;
	
	/** 资讯作者. **/
	private String author;
	
	/** 资讯内容. **/
	private String content;

	/** 作者email. **/
	private String authorEmail;
	
	/** 查询关键字. **/
	private String keywords;
	
	/** 资讯来源. **/
	private String soure;
	
	/** 资讯类型. **/
	private String articleType;
	
	/** 资讯是否显示. **/
	private String isShow;
	
	/** 文章图片URL. **/
	private String imgUrl;

	/** 是否放置首页. **/
	private String isHome;
	
	/** 资讯是否生效. **/
	private String isValidated;

	/** 资讯点击量. **/
	private int clickCount;
	
	/** 资讯添加时间. **/
	private String addTime;

}
