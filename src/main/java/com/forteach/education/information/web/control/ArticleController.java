package com.forteach.education.information.web.control;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.annotation.UserLoginToken;
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
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "文章资讯资料", tags = {"文章资讯资料操作信息"})
public class ArticleController  {

	private final ArticleService articleService;

	@Autowired
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	/**
	 * 保存资讯、资讯所属模块信息
	 */
	@UserLoginToken
	@ApiOperation(value = "保存资讯、资讯所属模块信息")
	@PostMapping("/saveOrUpdate")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleType", value = "资讯分类", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "title", value = "文章题目", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "courseId", value = "课程编号", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "classId", value = "班级编号", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "userId", value = "发布人编号", required = true, dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "images", value = "图片信息", dataTypeClass = List.class,required = true, paramType = "form"),
			@ApiImplicitParam(name = "imgUrl", value = "图片连接", dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "linkUrl", value = "文章连接", dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "description", value = "文章描述", dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "articleConten", value = "文章内容", dataType = "string", paramType = "form"),
			@ApiImplicitParam(name = "articleId", value = "文章编号", example = "修改必传, 新建发布不传值", dataType = "string", paramType = "form")
	})
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
	@ApiOperation(value = "获得资讯详情")
	@PostMapping("/findId")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "查询id", dataType = "string", required = true, paramType = "query")
	})
	public WebResult findById(@RequestBody ByIdRequest req) {
		MyAssert.isNull(req.getId(), DefineCode.ERR0010,"编号不能为空");
		Article article = articleService.findById(req.getId());
		ArticleResponse res=new ArticleResponse();
		UpdateUtil.copyProperties(article, res);
		//设置是否收藏、发布、点赞
		articleService.setStuTagType(res,article.getArticleId(),article.getUserId());
		return WebResult.okResult(res);
	}

	/**
	 *逻辑删除资讯内容
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "逻辑删除资讯内容")
	@PostMapping("/delId")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "查询id", dataType = "string", required = true, paramType = "query")
	})
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
	@ApiOperation(value = "所有资讯倒序分页获取")
	@PostMapping(path = "/findAllDesc")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "课程Id", name = "courseId", dataType = "string", paramType = "form"),
			@ApiImplicitParam(value = "学生Id", name = "studentId", dataType = "string", paramType = "form"),
			@ApiImplicitParam(value = "本人的用户Id", name = "userId", dataType = "string", paramType = "form"),
			@ApiImplicitParam(value = "分页排序字段", name = "sortVo", dataTypeClass = SortVo.class, paramType = "form")
	})
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
	@ApiOperation(value = "学生端所有资讯倒序分页获取")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "课程Id", name = "courseId", dataType = "string", paramType = "form"),
			@ApiImplicitParam(value = "学生Id", name = "studentId", dataType = "string", paramType = "form"),
			@ApiImplicitParam(value = "本人的用户Id", name = "userId", dataType = "string", paramType = "form"),
			@ApiImplicitParam(value = "分页排序字段", name = "sortVo", dataTypeClass = SortVo.class, paramType = "form")
	})
	@PostMapping("/findStuAllDesc")
	public WebResult findStuAllDesc(@RequestBody FindAllRequest req){
		SortVo sortVo = req.getSortVo();
		MyAssert.blank(String.valueOf(sortVo.getPage()), DefineCode.ERR0010, "当前页码不为空");
		MyAssert.blank(String.valueOf(sortVo.getSize()), DefineCode.ERR0010, "每页数量不为空");
		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
		if(StrUtil.isBlank(req.getCourseId())&&StrUtil.isBlank(req.getStudentId())){
			return WebResult.okResult(articleService.findAllDesc(page)
					.stream()
					.filter(Objects::nonNull)
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
	@UserLoginToken
	@ApiOperation(value = "点赞数量增加")
	@PostMapping("/addGood")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "资讯编号", dataType = "string", required = true, paramType = "form"),
			@ApiImplicitParam(name = "UserId", value = "操作人", dataType = "string", required = true, paramType = "form")
	})
	public WebResult addClickGood(@RequestBody AddClickGoodRequest req){
		MyAssert.isNull(req.getArticleId(), DefineCode.ERR0010,"资料编号不能为空");
		MyAssert.isNull(req.getUserId(), DefineCode.ERR0010,"用户编号不能为空");
		return WebResult.okResult(String.valueOf(articleService.addClickGood(req.getArticleId(),req.getUserId())));
	}

	/**
	 * 收藏数量增加
	 */
	@UserLoginToken
	@PostMapping("/addCollect")
	@ApiOperation(value = "收藏数量增加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "资讯编号", dataType = "string", required = true, paramType = "form"),
			@ApiImplicitParam(name = "UserId", value = "操作人", dataType = "string", required = true, paramType = "form")
	})
	public WebResult addCollect(@RequestBody AddClickGoodRequest req){
		MyAssert.isNull(req.getArticleId(), DefineCode.ERR0010,"资料编号不能为空");
		MyAssert.isNull(req.getUserId(), DefineCode.ERR0010,"用户编号不能为空");
		return WebResult.okResult(String.valueOf(articleService.addCollectCount(req.getArticleId(),req.getUserId())));
	}

	/**
	 * 收藏数量增加
	 */
	@UserLoginToken
	@PostMapping("/addNice")
	@ApiOperation(value = "收藏数量增加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "资讯编号", dataType = "string", required = true, paramType = "form"),
			@ApiImplicitParam(name = "value", value = "加精华值  true   false", dataType = "string", required = true, paramType = "form")
	})
	public WebResult addNice(@RequestBody AddNiceRequest req){
		MyAssert.isNull(req.getArticleId(), DefineCode.ERR0010,"资料编号不能为空");
		MyAssert.isNull(req.getValue(), DefineCode.ERR0010,"精华值不能为空");
		return WebResult.okResult(String.valueOf(articleService.addNice(req.getArticleId(),req.getValue())));
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
	@UserLoginToken
	@PostMapping("/delCollect")
	@ApiOperation(value = "删除收藏记录")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "用户id", dataType = "string", required = true, paramType = "form"),
			@ApiImplicitParam(name = "articleId", value = "资料编号", dataType = "string", required = true, paramType = "form")
	})
	public WebResult deleteCollect(@RequestBody DeleteMyArticleRequest req) {
		MyAssert.isNull(req.getArticleId(), DefineCode.ERR0010,"资料编号不能为空");
		MyAssert.isNull(req.getUserId(), DefineCode.ERR0010,"用户编号不能为空");
		return WebResult.okResult(articleService.delCollect(req.getArticleId(), req.getUserId()));
	}
}
