package com.forteach.education.information.service;

import cn.hutool.core.util.IdUtil;
import com.forteach.education.information.domain.MyArticle;
import com.forteach.education.information.dto.IArticle;
import com.forteach.education.information.repository.MyArticleDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MyArticleService {

	@Autowired
	private MyArticleDao myArticleDao;


	public List<IArticle> findByUserIdtagType(String userId, String tagType) {
		List<IArticle> list = myArticleDao.findByUserIdAndTagType(userId, tagType);
		return list;
	}

	@Transactional
	public String deleteMyArticleById(String id) {
		 myArticleDao.deleteById(id);
		return "Y";
	}

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

	@Transactional
	public String deleteByUserArticleId(String articleId, String userId) {
		int num = myArticleDao.deleteByUserAndArticleId(userId, articleId);
		String flag = "N";
		if (num > 0) {
			flag = "Y";
		}
		return flag;
	}

}
