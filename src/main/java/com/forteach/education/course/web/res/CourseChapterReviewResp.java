package com.forteach.education.course.web.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-24 18:16
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "课程章节评分信息")
public class CourseChapterReviewResp implements Serializable {

    @ApiModelProperty(name = "chapterId", value = "章节id", dataType = "string")
    private String chapterId;

    @ApiModelProperty(name = "averageScore", value = "评价平均分数", dataType = "string")
    private String averageScore;

    @ApiModelProperty(name = "reviewAmount", value = "评价数量", dataType = "int")
    private Integer reviewAmount;

    public CourseChapterReviewResp() {
    }

    public CourseChapterReviewResp(String chapterId, String averageScore, Integer reviewAmount) {
        this.chapterId = chapterId;
        this.averageScore = averageScore;
        this.reviewAmount = reviewAmount;
    }
}
