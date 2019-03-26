package com.forteach.education.course.web.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel(value = "课程添加成功")
public class CourseResp implements Serializable {

    @ApiModelProperty(value = "课程id", name = "courseId", dataType = "string")
    private String courseId;

    @ApiModelProperty(value = "课程名字", name = "courseName", dataType = "string")
    private String courseName;

    @ApiModelProperty(value = "courseNumber")
    private String courseNumber;

    @ApiModelProperty(value = "lessonPreparationType")
    private String lessonPreparationType;

    @ApiModelProperty(value = "topPicSrc")
    private String topPicSrc;

    @ApiModelProperty(value = "shareType")
    private String shareType;

    @ApiModelProperty(value = "courseDescribe")
    private String courseDescribe;

    @ApiModelProperty(value = "shareId")
    private String shareId;

    private String teachingType;

    public CourseResp(String courseId, String courseName, String courseNumber, String lessonPreparationType, String teachingType, String topPicSrc, String shareType, String courseDescribe, String shareId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.lessonPreparationType = lessonPreparationType;
        this.teachingType = teachingType;
        this.topPicSrc = topPicSrc;
        this.shareType = shareType;
        this.courseDescribe = courseDescribe;
        this.shareId = shareId;
    }

}
