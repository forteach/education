package com.forteach.education.course.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 11:21
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "保存课程章节评价")
public class CourseChapterReviewSaveReq implements Serializable {

    @ApiModelProperty(name = "chapterId", value = "章节id", dataType = "string", required = true)
    private String chapterId;

    @ApiModelProperty(name = "score", value = "评分", dataType = "int", required = true)
    private Integer score;

    @ApiModelProperty(hidden = true)
    private String studentId;
}
