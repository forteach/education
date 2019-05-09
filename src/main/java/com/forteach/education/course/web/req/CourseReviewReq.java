package com.forteach.education.course.web.req;

import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-9 16:20
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "分页查询课程评论记录")
public class CourseReviewReq implements Serializable {

    @ApiModelProperty(name = "courseId", value = "课程id", required = true, dataType = "string")
    private String courseId;

    @ApiModelProperty(value = "分页排序字段", name = "sortVo")
    private SortVo sortVo = new SortVo();

}
