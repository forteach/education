package com.forteach.education.course.web.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FindImpCoursewareReq implements Serializable {

    @ApiModelProperty(name = "chapterId", value = "章节编号", dataType = "string")
    private String chapterId;

    @ApiModelProperty(name = "importantType", dataType = "string", value = "资料领域：1教案 2课件")
    private String importantType;

    @ApiModelProperty(name = "datumType", value = "课件类型 1文件 3、视频", dataType = "string")
    private String datumType;

}
