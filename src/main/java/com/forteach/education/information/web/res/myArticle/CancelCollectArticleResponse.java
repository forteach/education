package com.forteach.education.information.web.res.myArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 取消收藏
 * @author admin
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelCollectArticleResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String flag;
}