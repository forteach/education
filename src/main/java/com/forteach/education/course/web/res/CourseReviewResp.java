package com.forteach.education.course.web.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-9 10:01
 * @version: 1.0
 * @description:
 */
@Data
@Builder
@ApiModel(value = "课程评价")
public class CourseReviewResp implements Serializable {

    @ApiModelProperty(name = "courseId", value = "课程id")
    private String courseId;

    @ApiModelProperty(name = "averageScore", value = "课程平均分")
    private String averageScore;

    @ApiModelProperty(name = "reviewAmount", value = "评价人数")
    private Integer reviewAmount;

    @ApiModelProperty(name = "courseReviewDescribe", value = "评论详情")
    CourseReviewDescResp courseReviewDescribe;

    public CourseReviewResp(String courseId, String averageScore, Integer reviewAmount, CourseReviewDescResp courseReviewDescribe) {
        this.courseId = courseId;
        this.averageScore = averageScore;
        this.reviewAmount = reviewAmount;
        this.courseReviewDescribe = courseReviewDescribe;
    }

    public CourseReviewResp() {
    }
}
