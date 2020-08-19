package com.forteach.education.information.web.req.artComment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author A18ccms a18ccms_gmail_com
 * @ClassName: AddCommentGoodRequest
 * @Description: 资讯评论点赞数量增加
 * @date 2016年5月3日 上午9:59:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "资讯评论点赞数量增加")
public class AddCommentGoodRequest implements Serializable {

    /**
     * 资讯评论编号.
     **/
    @ApiModelProperty(name = "commentId", value = "资讯评论编号", dataType = "string", required = true)
    private String commentId;
}