package com.forteach.education.information.web.control;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.information.domain.Article;
import com.forteach.education.information.service.ArticleService;
import com.forteach.education.information.web.req.article.SaveArticleRequest;
import com.forteach.education.information.web.valid.ArticleValide;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "文章资讯资料", tags = {"文章资讯资料操作信息"})
public class ArticleController  {

	@Autowired
	private ArticleService articleService;

	/**
	 * 保存资讯、资讯所属模块信息
	 */

	public Article save(SaveArticleRequest request) {

		Article article = null;
		// 验证资讯信息
		ArticleValide.saveValide(request);

		// 根据资讯编号未设置,判断为修改操作
		if (StrUtil.isNotBlank(request.getArticleId())) {
			// 根据资讯ID获得数据库详情
			article = findById(request.getArticleId());
		}

		// 设置资讯数据
		article = articleService.setDoMain(request);

		// 调用save方法
		return articleService.save(article);

	}

	public Article findById(String articleId) {
		Article article = articleService.findById(articleId);
		return article;
	}


	public String deleteArticleById(String articleId) {
		int result = articleService.deleteArticleById(articleId);
		MyAssert.eq(result, 0, DefineCode.ERR0013, "删除文章失败");
		return String.valueOf(result);
	}


}
