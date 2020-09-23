package com.forteach.education.information.web.res.notice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 保存收藏
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "保存收藏")
public class ListNoticeResponse implements Serializable {

    /**
     * 公告ID.
     */
    @ApiModelProperty(name = "noticeId", value = "公告ID", dataType = "string")
    private String noticeId;

    /**
     * 公告内容.
     */
    @ApiModelProperty(name = "content", value = "公告内容", dataType = "string")
    private String content;

    /**
     * 公告领域 P：全部 C：课程
     */
    @ApiModelProperty(name = "area", value = "公告领域 P：全部 C：课程", dataType = "string")
    private String area;

    @ApiModelProperty(name = "createTime", value = "创建时间", dataType = "string")
    public String createTime;
}