package com.forteach.education.course.web.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel(value = "课程添加成功")
public class CourseListResp {

    @ApiModelProperty(value = "CourseId")
    private String courseId;

    @ApiModelProperty(value = "courseName")
    private String courseName;

    @ApiModelProperty(value = "courseNumber")
    private String courseNumber;

    @ApiModelProperty(value = "lessonPreparationType")
    private String lessonPreparationType;

    @ApiModelProperty(value = "topPicSrc")
    private String topPicSrc;

    public CourseListResp(String courseId, String courseName, String courseNumber, String lessonPreparationType, String topPicSrc) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.lessonPreparationType = lessonPreparationType;
        this.topPicSrc = topPicSrc;
    }
}
