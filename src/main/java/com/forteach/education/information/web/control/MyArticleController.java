package com.forteach.education.information.web.control;

import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.information.domain.MyArticle;
import com.forteach.education.information.service.MyArticleService;
import com.forteach.education.information.web.req.myArticle.SaveMyArticleRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/myArticle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "我的资讯资料", tags = {"我的资讯资料（收藏、发布）"})
public class MyArticleController {
	@Autowired
	private MyArticleService myArticleService;

	public String findByUserIdArticleId(String userId, String articleId) {
 		return myArticleService.findByUserIdArticleId(userId,articleId);
	}

	public String deleteMyArticleById(String articleId, String postUserId, String userId) {
		 
		return myArticleService.deleteMyArticleById(userId,articleId,postUserId);
	}

	public MyArticle saveMyArticle(SaveMyArticleRequest request) {
		String articleId = request.getArticleId();
		String userId = request.getUserId();
		String postUserId=request.getPostUserId();//发布者编号
		//判断参数
		MyAssert.isNull(articleId, DefineCode.ERR0010,"文章编号不能为空");
		MyAssert.isNull(userId,DefineCode.ERR0010,"用户编号不能为空");
		
		MyArticle  myArticle=myArticleService.setMyArticle(articleId, userId, postUserId);
		return myArticleService.save(myArticle);
	}

	public String deleteByUserArticleId(String articleId, String userId) {
 		return myArticleService.deleteByUserArticleId(articleId,userId);
	}
}
