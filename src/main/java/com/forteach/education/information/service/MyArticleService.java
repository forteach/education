package com.forteach.education.information.service;

import com.forteach.education.information.domain.MyArticle;
import com.forteach.education.information.repository.MyArticleDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MyArticleService {

	@Autowired
	private MyArticleDao myArticleDao;


	public String findByUserIdArticleId(String userId, String articleId) {
		MyArticle myArticle = myArticleDao.findByUserIdArticleId(userId, articleId);
		String flag = "";
		if (myArticle == null) {
			flag = "N";
		}else{
		  flag = "Y";
		}
		return flag;
	}

	@Transactional
	public String deleteMyArticleById(String userId, String articleId, String postUserId) {
		int num = myArticleDao.deleteMyArticleById(userId, articleId, postUserId);
		String flag = "N";
		if (num > 0) {
			flag = "Y";
		}
		return flag;
	}

	public MyArticle setMyArticle(String articleId, String userId, String postUserId) {
		MyArticle myArticle = myArticleDao.find(articleId, userId, postUserId);
		if (myArticle == null) {
			// 创建资讯DOMAIN对象
			myArticle = new MyArticle();
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
		int num = myArticleDao.deleteByUserArticleId(userId, articleId);
		String flag = "N";
		if (num > 0) {
			flag = "Y";
		}
		return flag;
	}

}
