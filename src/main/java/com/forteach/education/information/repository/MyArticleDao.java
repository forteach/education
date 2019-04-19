package com.forteach.education.information.repository;

import com.forteach.education.information.domain.MyArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface MyArticleDao extends JpaRepository<MyArticle, String> {

	@Query("select  ma from MyArticle  ma  where  ma.userId=?1 and ma.articleId=?2")
	public MyArticle findByUserIdArticleId(String userId, String articleId);

	@Modifying
	@Query("delete from MyArticle     where   userId=?1 and  articleId=?2 and postUserId=?3")
	public int deleteMyArticleById(String userId, String articleId, String postUserId);

	@Query("select ma from MyArticle ma    where   ma.userId=?1 and  ma.articleId=?2 and ma.postUserId=?3")
	public MyArticle find(String articleId, String userId, String postUserId);
	
	@Modifying
	@Query("delete from MyArticle     where   userId=?1 and  articleId=?2")
	public int deleteByUserArticleId(String userId, String articleId);

}
