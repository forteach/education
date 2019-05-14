package com.forteach.education.course.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "获得重要课件列表")
public class FindImpCoursewareReq implements Serializable {

    @ApiModelProperty(name = "chapterId", value = "章节编号", dataType = "string", required = true)
    private String chapterId;

    @ApiModelProperty(name = "importantType", dataType = "string", value = "资料领域：1教案 2课件", required = true)
    private String importantType;

    @ApiModelProperty(name = "datumType", value = "课件类型 1文件 3、视频", dataType = "string", required = true)
    private String datumType;

}
