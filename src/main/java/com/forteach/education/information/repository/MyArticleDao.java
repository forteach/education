package com.forteach.education.information.repository;

import com.forteach.education.information.domain.MyArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MyArticleDao extends JpaRepository<MyArticle, String> {


	@Transactional(readOnly = true)
	Page<MyArticle> findAllByIsValidatedEqualsAndUserIdAndTagTypeOrderByCreateTimeDesc(String isValidated, String userId, int tagType, Pageable pageable);


	@Transactional(readOnly = true)
	List<MyArticle> findAllByIsValidatedEqualsAndUserIdAndTagType(String isValidated, String userId, int tagType);

	@Modifying(clearAutomatically = true)
	void deleteByUserIdAndArticleIdAndTagType(String articleId,String userId,int tagType);

	/**
	 * 判断我的标签类型的资讯是否已经存在
	 * @param articleId
	 * @param userId
	 * @param tagType
	 * @return
	 */
	@Transactional(readOnly = true)
    boolean existsByArticleIdAndUserIdAndTagType(String articleId,String userId,int tagType);

}
