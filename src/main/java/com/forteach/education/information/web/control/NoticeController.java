package com.forteach.education.information.web.control;

import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.information.service.NoticeService;
import com.forteach.education.information.web.req.notice.ByIdNoticeRequest;
import com.forteach.education.information.web.req.notice.FindIsValListRequest;
import com.forteach.education.information.web.req.notice.SaveNoticeRequest;
import com.forteach.education.information.web.res.notice.ListNoticeResponse;
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

import java.util.Objects;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;
import static java.util.stream.Collectors.toList;

/**
 * 简单公告
 */
@RestController
@RequestMapping(path = "/notice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "简单公告", tags = {"公告类型（公开、课程）"})
public class NoticeController {

	private final NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@UserLoginToken
	@ApiOperation(value = "保存/修改简单公告")
	@PostMapping("/save")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "noticeId", value = "公告ID", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "content", value = "公告内容", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "area", value = "公告领域 P：全部 C：课程", dataType = "string", paramType = "query")
	})
	public WebResult save(@RequestBody SaveNoticeRequest request) {
		return WebResult.okResult(noticeService.save(request.getNoticeId(),request.getContent(),request.getArea()));
	}

	@ApiOperation(value = "根据公告id查询详情")
	@PostMapping("/findById")
	@ApiImplicitParam(name = "noticeId", value = "公告ID", dataType = "string", required = true, paramType = "query")
	public WebResult findById(@RequestBody ByIdNoticeRequest request) {
		MyAssert.isNull(request.getNoticeId(), DefineCode.ERR0010, "公告id不为空");
 		return WebResult.okResult(noticeService.findById(request.getNoticeId()));
	}

	/**
	 * 根据Id删除公告
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "根据Id删除公告")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "noticeId", value = "公告ID", dataType = "string", required = true, paramType = "query")
	})
	@PostMapping("/delNotice")
	public WebResult deleteId(@RequestBody ByIdNoticeRequest request) {
		MyAssert.isNull(request.getNoticeId(), DefineCode.ERR0010, "公告id不为空");
		return WebResult.okResult(noticeService.deleteByNoticeId(request.getNoticeId()));
	}


	/**
	 * 获得分页倒序列表记录
	 * @param request
	 * @return
	 */
	@ApiOperation(value = "获得分页倒序列表记录")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "分页排序字段", name = "sortVo", required = true, dataTypeClass = SortVo.class, paramType = "query")
	})
	@PostMapping("/findAll")
	public WebResult findAll(@RequestBody FindIsValListRequest request) {
		SortVo sortVo = request.getSortVo();
		PageRequest page = PageRequest.of(sortVo.getPage(), sortVo.getSize());
		return WebResult.okResult(noticeService.findByIsValidatedDesc(TAKE_EFFECT_OPEN,page)
				.stream()
				.filter(Objects::nonNull)
				.map(item -> {
					ListNoticeResponse ar = new ListNoticeResponse();
					UpdateUtil.copyNullProperties(item, ar);
					return ar;
				})
				.collect(toList()));
	}
}