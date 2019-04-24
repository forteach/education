package com.forteach.education.information.service;

import cn.hutool.core.util.IdUtil;
import com.forteach.education.information.domain.MyArticle;
import com.forteach.education.information.repository.MyArticleDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MyArticleService {

	//MyArticle TagType 本人发布、收藏、点赞
	public final static int FABU=0;
	public final static int SHOUCANG=1;
	public final static int GOOD=2;

	@Autowired
	private MyArticleDao myArticleDao;

	public MyArticle setMyArticle(String id,String userId,String articleId) {
		MyArticle myArticle = myArticleDao.findById(id).get();
		if (myArticle == null) {
			// 创建资讯DOMAIN对象
			myArticle = new MyArticle();
			myArticle.setId(IdUtil.fastSimpleUUID());
		}
		// 获得页面设置的资讯值

		myArticle.setUserId(userId);
		myArticle.setArticleId(articleId);
		return myArticle;
	}

	public MyArticle save(MyArticle myArticle) {

		return myArticleDao.save(myArticle);
	}

	/**
	 * 判断我的资讯是否存在
	 * @param articleId
	 * @param userId
	 * @param tagType  我的文章标签类型 0：我发布的 1：我收藏的
	 * @return
	 */
   public boolean exixtsMyArticle(String articleId,String userId,int tagType){
		return myArticleDao.existsByArticleIdAndUserIdAndTagType(articleId,userId,tagType);
   }

	public List<MyArticle> findByUserIdtagType(String userId, int tagType) {
		List<MyArticle> list = myArticleDao.findByUserIdAndTagType(userId, tagType);
		return list;
	}

	@Transactional
	public String deleteMyArticleById(String id) {
		 myArticleDao.deleteById(id);
		return "Y";
	}

	@Transactional
	public String deleteMyArticle(String articleId,String userId,int tagType) {
		 myArticleDao.deleteByUserIdAndArticleIdAndTagType(articleId,userId,tagType);
		return "Y";
	}

}
