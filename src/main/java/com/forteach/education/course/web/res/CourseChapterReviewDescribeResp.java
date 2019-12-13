package com.forteach.education.course.web.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-24 18:13
 * @version: 1.0
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "学生课程章节评分")
public class CourseChapterReviewDescribeResp implements Serializable {

    @ApiModelProperty(name = "chapterId", value = "章节id", dataType = "string")
    private String chapterId;

    @ApiModelProperty(name = "studentId", value = "学生id", dataType = "string")
    private String studentId;

    @ApiModelProperty(name = "score", value = "评论分数", dataType = "int")
    private Integer score;
}
