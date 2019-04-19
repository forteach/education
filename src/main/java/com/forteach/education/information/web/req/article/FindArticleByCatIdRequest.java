package com.forteach.education.information.web.req.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
* @ClassName: FindArticleRequest 
* @Description: TODO(按资讯分类查询最新发布的资讯信息) 
* @author zjw
* @date
*
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindArticleByCatIdRequest implements Serializable {

	/** 检索资讯类别. **/
	private String catId;

	/** 分页页码. **/
	private int pageNo;

}