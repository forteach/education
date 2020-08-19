package com.forteach.education.information.web.req.notice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 保存收藏
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "保存收藏")
public class ByIdNoticeRequest implements Serializable {

	/**
	 * 公告ID.
	 */
	@ApiModelProperty(name = "noticeId", value = "公告ID", dataType = "string", required = true)
	private String noticeId;
}