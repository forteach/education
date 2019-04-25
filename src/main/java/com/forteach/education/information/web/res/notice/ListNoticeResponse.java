package com.forteach.education.information.web.res.notice;

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
public class ListNoticeResponse implements Serializable {

	/** 公告ID. */
	private String noticeId;

	/** 公告内容. */
	private String content;

	/**
	 * 公告领域 P：全部 C：课程
	 */
	private String area;

	public String createTime;
}