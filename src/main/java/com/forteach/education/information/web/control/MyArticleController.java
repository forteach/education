package com.forteach.education.information.web.control;

import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.information.domain.MyArticle;
import com.forteach.education.information.service.MyArticleService;
import com.forteach.education.information.web.req.myArticle.SaveMyArticleRequest;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/myArticle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "我的资讯资料", tags = {"我的资讯资料（收藏、发布）"})
public class MyArticleController {
	@Autowired
	private MyArticleService myArticleService;

	@PostMapping("/save")
	public WebResult saveMyArticle(SaveMyArticleRequest request) {
		String articleId = request.getArticleId();
		String userId = request.getUserId();
		String tagType=request.getTagType();//发布者编号
		//判断参数
		MyAssert.isNull(articleId, DefineCode.ERR0010,"编号不能为空");
		MyAssert.isNull(userId,DefineCode.ERR0010,"用户编号不能为空");

		MyArticle  myArticle=myArticleService.setMyArticle(articleId, userId, tagType);
		return WebResult.okResult(myArticleService.save(myArticle));
	}

	@PostMapping("/findUserIdtagType")
	public WebResult findByUserIdtagType(String userId, int tagType) {
 		return WebResult.okResult(myArticleService.findByUserIdtagType(userId,tagType));
	}

	/**
	 * 根据Id删除资讯
	 * @param id
	 * @return
	 */
	@PostMapping("/delId")
	public WebResult deleteId(String id) {
		return WebResult.okResult(myArticleService.deleteMyArticleById(id));
	}

	/**
	 * 根据Id删除资讯
	 * @param id
	 * @return
	 */
	@PostMapping("/delMyArticleId")
	public WebResult deleteMyArticle(String id) {
		String articleId="";
		String userId="";
		int tagType=0;
		return WebResult.okResult(myArticleService.deleteMyArticle(articleId,userId,tagType));
	}

}
