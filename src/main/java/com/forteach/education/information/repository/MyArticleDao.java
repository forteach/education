package com.forteach.education.information.repository;

import com.forteach.education.information.domain.MyArticle;
import com.forteach.education.information.dto.IArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface MyArticleDao extends JpaRepository<MyArticle, String> {

	public List<IArticle> findByUserIdAndTagType(String userId, String tagType);

	@Modifying
	public void deleteById(String id);
	
	@Modifying
	public int deleteByUserAndArticleId(String userId, String articleId);

}
