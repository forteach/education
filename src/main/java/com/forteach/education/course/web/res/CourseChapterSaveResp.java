package com.forteach.education.course.web.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-28 11:49
 * @Version: 1.0
 * @Description: 返回前端目录树结构
 */
@Data
@ApiModel(value = "课程章节添加、修改成功")
public class CourseChapterSaveResp implements Serializable {

    @ApiModelProperty(value = "chapterId")
    private String chapterId;

    @ApiModelProperty(value = "chapterId")
    private String chapterName;

    @ApiModelProperty(value = "courseId")
    private String courseId;

    @ApiModelProperty(value = "chapterParentId")
    private String chapterParentId;

    @ApiModelProperty(value = "sort")
    private String sort;

    @ApiModelProperty(value = "chapterType")
    private String chapterType;

    @ApiModelProperty(value = "chapterLevel")
    private String chapterLevel;

    @ApiModelProperty(value = "publish")
    private String publish;

}
