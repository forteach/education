package com.forteach.education.information.web.req.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
* @ClassName: TopArticleByIdRequest 
* @Description: 资讯点赞数量增加
* @author A18ccms a18ccms_gmail_com 
* @date 2016年5月3日 上午9:59:32 
*
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddClickGoodRequest implements Serializable {
	/** 资讯编号. **/
	private String articleId;
	/** 点赞操作人 **/
	private String UserId;


}
