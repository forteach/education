package com.forteach.education.information.web.control;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.domain.Article;
import com.forteach.education.information.service.ArticleService;
import com.forteach.education.information.web.req.article.*;
import com.forteach.education.information.web.res.article.ArticleResponse;
import com.forteach.education.information.web.valid.ArticleValide;
import com.forteach.education.util.UpdateUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "文章资讯资料", tags = {"文章资讯资料操作信息"})
public class ArticleController  {

	@Autowired
	private ArticleService articleService;

	/**
	 * 保存资讯、资讯所属模块信息
	 */

	@PostMapping("/saveOrUpdate")
	public WebResult save(SaveArticleRequest request) {

		Article article = null;
		// 验证资讯信息
		ArticleValide.saveValide(request);

		// 根据资讯编号未设置,判断为修改操作
		if (StrUtil.isNotBlank(request.getArticleId())) {
			// 根据资讯ID获得数据库详情
			article = articleService.findById(request.getArticleId());
		}

		// 设置资讯数据
		article = articleService.setDoMain(request);

		articleService.save(article);

		ArticleResponse res=new ArticleResponse();

		UpdateUtil.copyNullProperties(article, res);
		// 调用save方法
		return WebResult.okResult(res);

	}

	/**
	 * 获得资讯详情
	 * @param req
	 * @return
	 */
	@PostMapping("/findId")
	public WebResult findById(ByIdRequest req) {
		Article article = articleService.findById(req.getId());
		return WebResult.okResult(article);
	}

	/**
	 *逻辑删除资讯内容
	 * @param req
	 * @return
	 */
	@PostMapping("/delId")
	public WebResult deleteArticleById(ByIdRequest req) {
		int result = articleService.deleteArticleById(req.getId());
		MyAssert.eq(result, 0, DefineCode.ERR0013, "删除文章失败");
		return WebResult.okResult(result);
	}

//	/**
//	 * 根据课程Id获得资讯内容
//	 * @param req
//	 * @return
//	 */
//	@PostMapping("/findCourseId")
//	public WebResult findByCourseId(FindIdListRequest req){
//		SortVo sortVo = req.getSortVo();
//		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
//		return WebResult.okResult(articleService.findByCourseId(req.getId(),page)
//				.stream()
//				.map(item -> {
//					ArticleResponse ar = new ArticleResponse();
//					UpdateUtil.copyNullProperties(item, ar);
//					return ar;
//				})
//				.collect(toList()));
//	}

	/**
	 * 所有资讯倒序分页获取
	 * @param req
	 * @return
	 */
	@PostMapping("/findAllDesc")
	public WebResult findAllDesc(FindAllRequest req){
		SortVo sortVo = req.getSortVo();
		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
		if(StrUtil.isBlank(req.getCourseId())&&StrUtil.isBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findAllDesc(page)
					.stream()
					.map(item -> {
						ArticleResponse ar = new ArticleResponse();
						UpdateUtil.copyNullProperties(item, ar);
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isNotBlank(req.getCourseId())&&StrUtil.isBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByCourseId(req.getCourseId(),page)
					.stream()
					.map(item -> {
						ArticleResponse ar = new ArticleResponse();
						UpdateUtil.copyNullProperties(item, ar);
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isBlank(req.getCourseId())&&StrUtil.isNotBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByStudentId(req.getStudentId(),page)
					.stream()
					.map(item -> {
						ArticleResponse ar = new ArticleResponse();
						UpdateUtil.copyNullProperties(item, ar);
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isNotBlank(req.getCourseId())&&StrUtil.isNotBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByUserIdAndCourseIdByCreateTimeDesc(req.getStudentId(),req.getCourseId(),page)
					.stream()
					.map(item -> {
						ArticleResponse ar = new ArticleResponse();
						UpdateUtil.copyNullProperties(item, ar);
						return ar;
					})
					.collect(toList()));
		}

		return null;
	}

	/**
	 * 点赞数量增加
	 */
	@PostMapping("/addClickGood")
	public WebResult addClickGood(addClickGoodRequest req){
		return WebResult.okResult(articleService.addClickGood(req.getArticleId()));
	}
}
