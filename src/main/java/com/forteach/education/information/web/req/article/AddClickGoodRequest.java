package com.forteach.education.information.web.req.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author A18ccms a18ccms_gmail_com
 * @ClassName: TopArticleByIdRequest
 * @Description: 资讯点赞数量增加
 * @date 2016年5月3日 上午9:59:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "资讯点赞数量增加")
public class AddClickGoodRequest implements Serializable {

    /**
     * 资讯编号.
     **/
    @ApiModelProperty(name = "articleId", value = "资讯编号", dataType = "string", required = true)
    private String articleId;

    /**
     * 点赞操作人
     **/
    @ApiModelProperty(name = "UserId", value = "操作人", dataType = "string", required = true)
    private String UserId;
}
