package com.forteach.education.information.web.req.artComment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
* @ClassName: AddCommentGoodRequest
* @Description: 资讯评论点赞数量增加
* @author A18ccms a18ccms_gmail_com 
* @date 2016年5月3日 上午9:59:32 
*
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentGoodRequest implements Serializable {
	/** 资讯评论编号. **/
	private String commentId;

}
