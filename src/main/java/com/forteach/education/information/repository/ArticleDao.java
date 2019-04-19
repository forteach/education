package com.forteach.education.information.repository;

import java.util.List;

import com.forteach.education.information.domain.Article;
import com.forteach.education.information.dto.IArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

	/**
	 * 分页查看资讯信息
	 */
	public Page<IArticle> findByCourseIdOrderByCreateTimeDesc(String courseId, Pageable pageable);

	public Page<IArticle> findAllByOrderByCreateTimeDesc(Pageable pageable);

	@Modifying
	@Query("update  Article  set isValidated='1' where articleId in(?1)")
	public int delMoreByArticleIds(List<String> articleIds);
	
	@Modifying
	@Query("update  Article  set isValidated='1' where articleId =?1")
	public int deleteArticleById(String articleId);

	/**
	 * 收藏数量
	 * @return
	 */
	@Modifying
	@Query("update  Article  set collectCount=collectCount+1 where articleId =?1")
	public int addCollectCount(String articleId);

	/**
	 * 点赞数量
	 * @return
	 */
	@Modifying
	@Query("update  Article  set clickGood=clickGood+1 where articleId =?1")
	public int addClickGood(String articleId);

	/**
	 * 点击数量
	 * @return
	 */
	@Modifying
	@Query("update  Article  set clickCount=clickCount+1 where articleId =?1")
	public int addClickCount(String articleId);



}
