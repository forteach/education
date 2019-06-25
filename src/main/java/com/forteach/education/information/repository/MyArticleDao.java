package com.forteach.education.information.repository;

import com.forteach.education.information.domain.MyArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MyArticleDao extends JpaRepository<MyArticle, String> {

	@Transactional(readOnly = true)
	public MyArticle findByPkId(String pkId);

	@Transactional(readOnly = true)
	public List<MyArticle> findByUserIdAndTagType(String userId, int tagType);

	@Modifying(clearAutomatically = true)
	public void deleteByPkId(String id);

	@Modifying(clearAutomatically = true)
	public void deleteByUserIdAndArticleIdAndTagType(String articleId,String userId,int tagType);

	/**
	 * 判断我的标签类型的资讯是否已经存在
	 * @param articleId
	 * @param userId
	 * @param tagType
	 * @return
	 */
    public boolean existsByArticleIdAndUserIdAndTagType(String articleId,String userId,int tagType);

}
