package com.forteach.education.information.web.req.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
* @ClassName: TopArticleByIdRequest 
* @Description: TODO(对资讯信息置顶) 
* @author A18ccms a18ccms_gmail_com 
* @date 2016年5月3日 上午9:59:32 
*
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopArticleByIdRequest implements Serializable {
	/** 资讯编号. 当为批量处理时，已逗号分隔**/
	private String articleId;
	/** 设置批量处理,不需要批量处理，该参数不用设置. **/
	private String isBrtch;
	/** 模块名称 **/
	private String modId;

}
