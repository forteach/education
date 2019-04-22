package com.forteach.education.information.repository;

import com.forteach.education.information.domain.MyArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;


public interface MyArticleDao extends JpaRepository<MyArticle, String> {

	public List<MyArticle> findByUserIdAndTagType(String userId, String tagType);

	@Modifying
	public void deleteById(String id);
	


}
