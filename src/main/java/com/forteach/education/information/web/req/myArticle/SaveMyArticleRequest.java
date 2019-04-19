package com.forteach.education.information.web.req.myArticle;

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
public class SaveMyArticleRequest implements Serializable {
	
	private String articleId;
	
	private String userId;
	
	private String postUserId;//发布者编号

	private String flag;


}