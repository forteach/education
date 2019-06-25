package com.forteach.education.information.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 我收藏和发布的文章
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "my_article")
@org.hibernate.annotations.Table(appliesTo = "my_article", comment = "我的文章")
@AllArgsConstructor
@NoArgsConstructor
public class MyArticle extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pk_Id", nullable = false, columnDefinition = "VARCHAR(40) COMMENT '主键id'")
	private String pkId;

	@Column(name = "article_id", nullable = false, columnDefinition = "VARCHAR(40) COMMENT '文章id'")
	private String articleId;

	@Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(40) COMMENT '用户id'")
	private String userId;

	/**
	 * 我的文章标签类型 0：我发布的 1：我收藏的 2:点赞
	 */
	@Column(name = "tag_type", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '我的文章标签类型 0：我发布的 1：我收藏的 2:点赞'")
	private int tagType;
}
