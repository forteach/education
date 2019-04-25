package com.forteach.education.information.web.req.artComment;

import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-29 16:21
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "资讯评论回复")
public class SaveReplyRequest implements Serializable {

    @ApiModelProperty(value = "评论ID", name = "commentId")
    private String commentId;

    @ApiModelProperty(value = "回复内容", name = "reply")
    private String reply;

    @ApiModelProperty(value = "回复人名称", name = "replyUserName")
    private String replyUserName;

}
