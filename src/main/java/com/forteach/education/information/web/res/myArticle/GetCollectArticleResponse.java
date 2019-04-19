package com.forteach.education.information.web.res.myArticle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 收藏列表
 * @author admin
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCollectArticleResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String imgUrl;//头像地址
	
	private String alias;//名称
	
	private String title;//标题
	
	private int clickCount;//点击量
	
	private int collectCount;//收藏量
	
	private String addTime;//添加时间

}
