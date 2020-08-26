package com.forteach.education.statistics.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/21 10:30
 * @Version: v1.0
 * @Modified：
 * @Description: 课程分页查询统计
 */
@Data
@ApiModel(value = "分页查询统计课程信息")
public class CountCoursePageAll extends BaseCountPageAll{

    @ApiModelProperty(name = "courseName", value = "课程名称", dataType = "string")
    private String courseName;
}