package com.forteach.education.statistics.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/21 10:36
 * @Version: v1.0
 * @Modified：教学统计
 * @Description: 分页查询院系
 */
@Data
@ApiModel(value = "分页查询院系统计")
public class CountFacultyPageAll extends BaseCountPageAll{

    @ApiModelProperty(name = "teacherName", value = "教师名称", dataType = "string")
    private String teacherName;

    @ApiModelProperty(name = "teachingOfficeName", value = "教研室名称", dataType = "string")
    private String teachingOfficeName;
}