package com.forteach.education.information.web.control;

import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.service.MyArticleService;
import com.forteach.education.information.service.NoticeService;
import com.forteach.education.information.web.req.myArticle.DeleteMyArticleRequest;
import com.forteach.education.information.web.req.notice.ByIdNoticeRequest;
import com.forteach.education.information.web.req.notice.FindIsValListRequest;
import com.forteach.education.information.web.req.notice.SaveNoticeRequest;
import com.forteach.education.information.web.res.article.ArticleStuListResponse;
import com.forteach.education.information.web.res.notice.ListNoticeResponse;
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

/**
 * 简单公告
 */
@RestController
@RequestMapping(path = "/notice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "简单公告", tags = {"公告类型（公开、课程）"})
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	@PostMapping("/save")
	public WebResult saveMyArticle(SaveNoticeRequest request) {
		return WebResult.okResult(noticeService.save(request.getNoticeId(),request.getContent(),request.getArea()));
	}

	@PostMapping("/findById")
	public WebResult findByUserIdtagType(ByIdNoticeRequest request) {
 		return WebResult.okResult(noticeService.findById(request.getNoticeId()));
	}

	/**
	 * 根据Id删除公告
	 * @param id
	 * @return
	 */
	@PostMapping("/delNotice")
	public WebResult deleteId(String id) {
		return WebResult.okResult(noticeService.deleteByNoticeId(id));
	}


	/**
	 * 获得分页倒序列表记录
	 * @param request
	 * @return
	 */
	@PostMapping("/delCollect")
	public WebResult deleteMyArticle(@RequestBody FindIsValListRequest request) {
		SortVo sortVo = request.getSortVo();
		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
		return WebResult.okResult(noticeService.findByIsValidatedDesc(request.getIsVal(),page)
				.stream()
				.map(item -> {
					ListNoticeResponse ar = new ListNoticeResponse();
					UpdateUtil.copyNullProperties(item, ar);
					return ar;
				})
				.collect(toList()));
	}

}
