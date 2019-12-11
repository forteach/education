package com.forteach.education.information.web.req.myArticle;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 取消收藏
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "取消收藏")
public class DeleteMyArticleRequest implements Serializable {

    @ApiModelProperty(name = "userId", value = "用户id", required = true, dataType = "string")
    private String userId;

    @ApiModelProperty(name = "articleId", value = "资料编号", required = true, dataType = "string")
    private String articleId;

}