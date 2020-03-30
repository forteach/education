package com.forteach.education.course.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-27 16:08
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "学习查询状态")
public class CourseStudyReq implements Serializable {

    @ApiModelProperty(name = "studyStatus", value = "学习状态 0 未学习　1 在学习　2 已完结", dataType = "int")
    private Integer studyStatus;

}
