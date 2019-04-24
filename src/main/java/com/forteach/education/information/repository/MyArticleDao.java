package com.forteach.education.information.repository;

import com.forteach.education.information.domain.MyArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;


public interface MyArticleDao extends JpaRepository<MyArticle, String> {

	public List<MyArticle> findByUserIdAndTagType(String userId, int tagType);

	@Modifying
	public void deleteById(String id);

	@Modifying
	public void deleteByUserIdAndArticleIdAndTagType(String articleId,String userId,int tagType);

	//判断我的标签类型的资讯是否已经存在
    public boolean existsByArticleIdAndUserIdAndTagType(String articleId,String userId,int tagType);

}
