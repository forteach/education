package com.forteach.education.count.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 14:59
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "查询对应的课程章节统计信息数据", description = "查询课程章节对应的数据")
public class CourseCountReq implements Serializable {

    @ApiModelProperty(name = "courseId", value = "课程id", dataType = "string", required = true)
    private String courseId;

    @ApiModelProperty(name = "chapterId", value = "章节id", dataType = "string", required = true)
    private String chapterId;

    @ApiModelProperty(name = "classId", value = "班级id", dataType = "string", required = true)
    private String classId;

    @ApiModelProperty(hidden = true)
    private String teacherId;

}
