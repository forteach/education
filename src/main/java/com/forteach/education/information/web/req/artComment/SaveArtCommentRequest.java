package com.forteach.education.information.web.req.artComment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建资讯评论
 * 
 * @author zjw
 *
 */
@Data
@ApiModel(value = "创建资讯评论")
@AllArgsConstructor
@NoArgsConstructor
public class SaveArtCommentRequest {

	/** 评论编号. **/
	@ApiModelProperty(name = "commentId", value = "评论编号", dataType = "string", required = true)
	private String commentId;

	/** 文章编号. **/
	@ApiModelProperty(name = "articleId", value = "评论的内容", dataType = "string", required = true)
	private String articleId;

	/** 评论文章用户编号. **/
	@ApiModelProperty(name = "userId", value = "评论的内容", dataType = "string")
	private String userId;

	/** 评论的内容. **/
	@ApiModelProperty(name = "content", value = "评论的内容", dataType = "string", required = true)
	private String content;

	/**评论人员类型 S 学生  T 教师*/
	@ApiModelProperty(name = "userType", value = "评论人员类型 S 学生  T 教师", dataType = "string", required = true)
	private String userType;

}