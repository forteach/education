package com.forteach.education.course.web.res;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-9 10:08
 * @version: 1.0
 * @description:
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "评论详情")
public class CourseReviewDescResp implements Serializable {

    @ApiModelProperty(name = "studentId", value = "学生id")
    private String studentId;

    @ApiModelProperty(name = "studentName", value = "学生名字")
    private String studentName;

    @ApiModelProperty(name = "portrait", value = "学生头像")
    private String portrait;

    @ApiModelProperty(name = "reviewId", value = "评论id")
    private String reviewId;

    @ApiModelProperty(name = "reviewDescribe", value = "评论内容")
    private String reviewDescribe;

    @ApiModelProperty(name = "classId", value = "班级id")
    private String classId;

    @ApiModelProperty(name = "className", value = "班级名字")
    private String className;

    @ApiModelProperty(name = "score", value = "评论分数")
    private Integer score;

    @ApiModelProperty(name = "createTime", value = "创建时间")
    private String createTime;

    @ApiModelProperty(name = "reply", value = "老师回复的内容")
    private String reply;

    @ApiModelProperty(name = "replyTime", value = "回复时间")
    private String replyTime;

    @ApiModelProperty(name = "teacherId", value = "教师id")
    private String teacherId;

    @ApiModelProperty(name = "teacherName", value = "教师名字")
    private String teacherName;

}
