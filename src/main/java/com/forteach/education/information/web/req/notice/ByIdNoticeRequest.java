package com.forteach.education.information.web.req.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 保存收藏
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ByIdNoticeRequest implements Serializable {

	/** 公告ID. */
	private String noticeId;

}