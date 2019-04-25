package com.forteach.education.information.web.control;

import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.domain.ArticleComment;
import com.forteach.education.information.service.ArticleCommentService;
import com.forteach.education.information.web.req.artComment.AddCommentGoodRequest;
import com.forteach.education.information.web.req.artComment.FindArticleIdRequest;
import com.forteach.education.information.web.req.artComment.SaveArtCommentRequest;
import com.forteach.education.information.web.req.artComment.SaveReplyRequest;
import com.forteach.education.information.web.res.artComment.SaveArtCommentResponse;
import com.forteach.education.information.web.res.article.ArticleListResponse;
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
@RequestMapping(path = "/articleComment", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "文章资讯资料", tags = {"文章资讯资料操作信息"})
public class ArticleCommentController {

	@Autowired
	private ArticleCommentService articleCommentService;

	/**
	 * 保存资讯、资讯所属模块信息
	 */

	@PostMapping("/saveOrUpdate")
	public WebResult save(SaveArtCommentRequest request) {

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
	@PostMapping("/findArticleId")
	public WebResult findArticleComment(FindArticleIdRequest req){
		String artId=req.getArticleId();
		SortVo sortVo = req.getSortVo();
		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
			return WebResult.okResult(articleCommentService.findByArticleId(artId,page)
					.stream()
					.map(item -> {
						ArticleListResponse ar = new ArticleListResponse();
						UpdateUtil.copyNullProperties(item, ar);
						return ar;
					})
					.collect(toList()));

	}

	/**
	 * 点赞数量增加
	 */
	@PostMapping("/addGood")
	public WebResult addClickGood(@RequestBody AddCommentGoodRequest req){
		return WebResult.okResult(String.valueOf(articleCommentService.addClickGood(req.getCommentId())));
	}

	/**
	 * 点赞数量增加
	 */
	@PostMapping("/saveReply")
	public WebResult saveReply(@RequestBody SaveReplyRequest req){
		return WebResult.okResult(String.valueOf(articleCommentService.saveReply(req.getReply(),req.getCommentId(),req.getReplyUserName())));
	}

}
