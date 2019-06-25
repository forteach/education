package com.forteach.education.information.web.control;

import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.domain.ArticleComment;
import com.forteach.education.information.service.ArticleCommentService;
import com.forteach.education.information.web.req.artComment.AddCommentGoodRequest;
import com.forteach.education.information.web.req.artComment.FindArticleIdRequest;
import com.forteach.education.information.web.req.artComment.SaveArtCommentRequest;
import com.forteach.education.information.web.req.artComment.SaveReplyRequest;
import com.forteach.education.information.web.res.artComment.ArtCommentListResponse;
import com.forteach.education.information.web.res.artComment.SaveArtCommentResponse;
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

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/articleComment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "文章资讯资料", tags = {"文章资讯资料操作信息"})
public class ArticleCommentController {

	private final ArticleCommentService articleCommentService;

	@Autowired
	public ArticleCommentController(ArticleCommentService articleCommentService) {
		this.articleCommentService = articleCommentService;
	}

	/**
	 * 保存资讯、资讯所属模块信息
	 */

	@UserLoginToken
	@ApiOperation(value = "保存资讯、资讯所属模块信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId", value = "评论文章用户编号", dataType = "string", paramType = "form", example = "为空是保存，否则修改"),
			@ApiImplicitParam(name = "commentId", value = "评论编号", dataType = "string", required = true, paramType = "form"),
			@ApiImplicitParam(name = "articleId", value = "文章编号", dataType = "string", required = true, paramType = "form"),
			@ApiImplicitParam(name = "content", value = "评论的内容", dataType = "string", required = true, paramType = "form"),
			@ApiImplicitParam(name = "userType", value = "评论人员类型 S 学生  T 教师", dataType = "string", required = true)
	})
	@PostMapping("/saveOrUpdate")
	public WebResult save(@RequestBody SaveArtCommentRequest request) {

		MyAssert.isNull(request.getArticleId(), DefineCode.ERR0010,"资料编号不能为空");
		MyAssert.isNull(request.getUserId(), DefineCode.ERR0010,"评论人编号不能为空");
		MyAssert.isNull(request.getContent(), DefineCode.ERR0010,"评论内容不能为空");
		MyAssert.isNull(request.getUserType(), DefineCode.ERR0010,"评论人类型不能为空");

		ArticleComment artcomment=articleCommentService.save(request);

		SaveArtCommentResponse res=new SaveArtCommentResponse();

		UpdateUtil.copyNullProperties(artcomment, res);
		// 调用save方法
		return WebResult.okResult(res);
	}

	/**
	 * 所有资讯倒序分页获取
	 * @param req
	 * @return
	 */
	@ApiOperation(value = "所有资讯倒序分页获取")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "资讯Id", name = "articleId", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "分页排序字段", name = "sortVo", dataTypeClass = SortVo.class, required = true, paramType = "query")
	})
	@PostMapping("/findArticleId")
	public WebResult findArticleComment(@RequestBody FindArticleIdRequest req){
		String artId=req.getArticleId();
		MyAssert.isNull(artId, DefineCode.ERR0010,"资料编号不能为空");
		SortVo sortVo = req.getSortVo();
		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
			return WebResult.okResult(articleCommentService.findByArticleId(artId,page)
					.stream()
					.map(item -> {
						ArtCommentListResponse ar = new ArtCommentListResponse();
						UpdateUtil.copyNullProperties(item, ar);
						return ar;
					})
					.collect(toList()));

	}

	/**
	 * 点赞数量增加
	 */
	@PostMapping("/addGood")
	@ApiOperation(value = "点赞数量增加")
	@ApiImplicitParam(name = "commentId", value = "资讯评论编号", dataType = "string", required = true, paramType = "query")
	public WebResult addClickGood(@RequestBody AddCommentGoodRequest req){
		MyAssert.isNull(req.getCommentId(), DefineCode.ERR0010,"评论编号不能为空");
		return WebResult.okResult(String.valueOf(articleCommentService.addClickGood(req.getCommentId())));
	}

	/**
	 * 点赞数量增加
	 */
	@UserLoginToken
	@ApiOperation(value = "点赞数量增加")
	@PostMapping("/saveReply")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "评论ID", name = "commentId", required = true, dataType = "string"),
			@ApiImplicitParam(value = "回复内容", name = "reply", required = true, dataType = "string"),
			@ApiImplicitParam(value = "回复人名称", name = "replyUserName", required = true, dataType = "string")
	})
	public WebResult saveReply(@RequestBody SaveReplyRequest req){
		MyAssert.isNull(req.getReply(), DefineCode.ERR0010,"评论回复内容不能为空");
		MyAssert.isNull(req.getCommentId(), DefineCode.ERR0010,"评论编号不能为空");
		MyAssert.isNull(req.getReplyUserName(), DefineCode.ERR0010,"评论回复人不能为空");
		return WebResult.okResult(String.valueOf(articleCommentService.saveReply(req.getReply(),req.getCommentId(),req.getReplyUserName())));
	}

}
