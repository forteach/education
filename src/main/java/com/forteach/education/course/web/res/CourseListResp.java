package com.forteach.education.course.web.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@AllArgsConstructor
@ApiModel(value = "课程添加成功", description = "课程信息")
public class CourseListResp implements Serializable {

    @ApiModelProperty(value = "课程id", name = "courseId", dataType = "string")
    private String courseId;

    @ApiModelProperty(name = "courseName", value = "课程名称", dataType = "string")
    private String courseName;

    @ApiModelProperty(value = "课程编号", name = "courseNumber", dataType = "string")
    private String courseNumber;

    @ApiModelProperty(name = "lessonPreparationType", value = "备课类型　1、单人备课 2、集体备课", dataType = "string")
    private String lessonPreparationType;

    @ApiModelProperty(value = "封面图片路径", name = "topPicSrc", dataType = "string")
    private String topPicSrc;

    @ApiModelProperty(value = "课程描述", name = "courseDescribe", dataType = "string")
    private String courseDescribe;

    @ApiModelProperty(value = "章节id", name = "chapterId", dataType = "string")
    private String chapterId;

    @ApiModelProperty(value = "章节名称", name = "chapterName", dataType = "string")
    private String chapterName;

    @ApiModelProperty(value = "已经上课的课程id", name = "joinChapterId", dataType = "string")
    private String joinChapterId;

    @ApiModelProperty(value = "已经上课的课程名称", name = "joinChapterName", dataType = "string")
    private String joinChapterName;

    public CourseListResp(String courseId, String courseName, String courseNumber, String lessonPreparationType, String topPicSrc) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.lessonPreparationType = lessonPreparationType;
        this.topPicSrc = topPicSrc;
    }

    public CourseListResp() {
    }
}
