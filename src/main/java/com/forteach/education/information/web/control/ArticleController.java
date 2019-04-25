package com.forteach.education.information.web.control;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.domain.Article;
import com.forteach.education.information.service.ArticleService;
import com.forteach.education.information.web.req.article.*;
import com.forteach.education.information.web.req.myArticle.DeleteMyArticleRequest;
import com.forteach.education.information.web.res.article.ArticleListResponse;
import com.forteach.education.information.web.res.article.ArticleResponse;
import com.forteach.education.information.web.res.article.ArticleStuListResponse;
import com.forteach.education.information.web.valid.ArticleValide;
import com.forteach.education.util.UpdateUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public WebResult save(@RequestBody SaveArticleRequest request) {

		// 验证资讯信息
		ArticleValide.saveValide(request);

		// 设置资讯数据
		Article article = articleService.setDoMain(request);

		articleService.save(article,request.getImages());

		ArticleResponse res=new ArticleResponse();

		UpdateUtil.copyProperties(article, res);
		// 调用save方法
		return WebResult.okResult(res);

	}

	/**
	 * 获得资讯详情
	 * @param req
	 * @return
	 */
	@PostMapping("/findId")
	public WebResult findById(@RequestBody ByIdRequest req) {
		MyAssert.isNull(req.getId(), DefineCode.ERR0010,"编号不能为空");
		Article article = articleService.findById(req.getId());
		ArticleResponse res=new ArticleResponse();
		UpdateUtil.copyProperties(article, res);
		//设置是否收藏、发布、点赞
		articleService.setStuTagType(res,article.getArticleId(),article.getUserId());
		return WebResult.okResult(article);
	}

	/**
	 *逻辑删除资讯内容
	 * @param req
	 * @return
	 */
	@PostMapping("/delId")
	public WebResult deleteArticleById(@RequestBody ByIdRequest req) {
		MyAssert.isNull(req.getId(), DefineCode.ERR0010,"编号不能为空");
		int result = articleService.deleteArticleById(req.getId());
		MyAssert.eq(result, 0, DefineCode.ERR0013, "删除文章失败");
		return WebResult.okResult(result);
	}

	/**
	 * 所有资讯倒序分页获取
	 * @param req
	 * @return
	 */
	@PostMapping("/findAllDesc")
	public WebResult findAllDesc(@RequestBody FindAllRequest req){
		SortVo sortVo = req.getSortVo();
		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
		if(StrUtil.isBlank(req.getCourseId())&&StrUtil.isBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findAllDesc(page)
					.stream()
					.map(item -> {
						ArticleListResponse ar = new ArticleListResponse();
						UpdateUtil.copyProperties(item, ar);
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isNotBlank(req.getCourseId())&&StrUtil.isBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByCourseId(req.getCourseId(),page)
					.stream()
					.map(item -> {
						ArticleListResponse ar = new ArticleListResponse();
						UpdateUtil.copyProperties(item, ar);
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isBlank(req.getCourseId())&&StrUtil.isNotBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByStudentId(req.getStudentId(),page)
					.stream()
					.map(item -> {
						ArticleStuListResponse ar = new ArticleStuListResponse();
						UpdateUtil.copyProperties(item, ar);
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isNotBlank(req.getCourseId())&&StrUtil.isNotBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByUserIdAndCourseIdByCreateTimeDesc(req.getStudentId(),req.getCourseId(),page)
					.stream()
					.map(item -> {
						ArticleListResponse ar = new ArticleListResponse();
						UpdateUtil.copyProperties(item, ar);
						return ar;
					})
					.collect(toList()));
		}

		return null;
	}

	/**
	 * 学生端所有资讯倒序分页获取
	 * @param req
	 * @return
	 */
	@PostMapping("/findStuAllDesc")
	public WebResult findStuAllDesc(@RequestBody FindAllRequest req){
		SortVo sortVo = req.getSortVo();
		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
		if(StrUtil.isBlank(req.getCourseId())&&StrUtil.isBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findAllDesc(page)
					.stream()
					.map(item -> {
						ArticleStuListResponse ar = new ArticleStuListResponse();
						UpdateUtil.copyProperties(item, ar);
						//设置是否收藏、发布、点赞
						articleService.setStuTagType(ar,item.getArticleId(),req.getUserId());
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isNotBlank(req.getCourseId())&&StrUtil.isBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByCourseId(req.getCourseId(),page)
					.stream()
					.map(item -> {
						ArticleStuListResponse ar = new ArticleStuListResponse();
						UpdateUtil.copyProperties(item, ar);
						//设置是否收藏、发布、点赞
						articleService.setStuTagType(ar,item.getArticleId(),req.getStudentId());
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isBlank(req.getCourseId())&&StrUtil.isNotBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByStudentId(req.getStudentId(),page)
					.stream()
					.map(item -> {
						ArticleStuListResponse ar = new ArticleStuListResponse();
						UpdateUtil.copyProperties(item, ar);
						//设置是否收藏、发布、点赞
						articleService.setStuTagType(ar,item.getArticleId(),req.getStudentId());
						return ar;
					})
					.collect(toList()));
		}

		if(StrUtil.isNotBlank(req.getCourseId())&&StrUtil.isNotBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findByUserIdAndCourseIdByCreateTimeDesc(req.getStudentId(),req.getCourseId(),page)
					.stream()
					.map(item -> {
						ArticleStuListResponse ar = new ArticleStuListResponse();
						UpdateUtil.copyProperties(item, ar);
						//设置是否收藏、发布、点赞
						articleService.setStuTagType(ar,item.getArticleId(),req.getStudentId());
						return ar;
					})
					.collect(toList()));
		}

		return null;
	}



	/**
	 * 点赞数量增加
	 */
	@PostMapping("/addGood")
	public WebResult addClickGood(@RequestBody AddClickGoodRequest req){
		return WebResult.okResult(String.valueOf(articleService.addClickGood(req.getArticleId(),req.getUserId())));
	}

	/**
	 * 收藏数量增加
	 */
	@PostMapping("/addCollect")
	public WebResult addCollect(@RequestBody AddClickGoodRequest req){
		MyAssert.isNull(req.getArticleId(), DefineCode.ERR0010,"资料编号不能为空");
		MyAssert.isNull(req.getUserId(), DefineCode.ERR0010,"用户编号不能为空");
		return WebResult.okResult(String.valueOf(articleService.addCollectCount(req.getArticleId(),req.getUserId())));
	}

//	/**
//	 * 删除点赞记录
//	 * @param req
//	 * @return
//	 */
//	@PostMapping("/delGood")
//	public WebResult delGood(@RequestBody DeleteMyArticleRequest req) {
//		return WebResult.okResult(articleService.delGood(req.getArticleId(),req.getUserId()));
//	}

	/**
	 * 删除收藏记录
	 * @param req
	 * @return
	 */

	@PostMapping("/delCollect")
	public WebResult deleteCollect(@RequestBody DeleteMyArticleRequest req) {
		MyAssert.isNull(req.getArticleId(), DefineCode.ERR0010,"资料编号不能为空");
		MyAssert.isNull(req.getUserId(), DefineCode.ERR0010,"用户编号不能为空");
		return WebResult.okResult(articleService.delCollect(req.getArticleId(), req.getUserId()));
	}
}
