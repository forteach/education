package com.forteach.education.information.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.forteach.education.common.domain.Entitys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 我收藏和发布的文章
 */
@Data
@Entity
@Table(name = "my_article")
@org.hibernate.annotations.Table(appliesTo = "my_article", comment = "我的文章")
@AllArgsConstructor
@NoArgsConstructor
public class MyArticle extends Entitys implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 40, name = "id", nullable = false)
	private String id;

	@Column(length = 40, name = "article_id", nullable = false)
	private String articleId;//文章id

	@Column(length = 40, name = "user_id", nullable = false)
	private String userId;//用户id

	/**
	 * 我的文章标签类型 0：我发布的 1：我收藏的
	 */
	@Column(name = "tag_type", nullable = false)
	private int tagType;//文章id
}
