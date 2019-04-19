package com.forteach.education.information.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

public class MyArticlePK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 40, name = "user_id", nullable = false)
	private String userId;//用户id
	
	@Id
	@Column(length = 40, name = "article_id", nullable = false)
	private String articleId;//文章id

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
}
