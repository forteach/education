package com.forteach.education.information.web.req.myArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取收藏列表
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCollectArticleRequest {
	
	private String userId;
	
	/** 分页页码. **/
	private int pageNo;

}