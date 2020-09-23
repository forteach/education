package com.forteach.education.statistics.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/21 10:33
 * @Version: v1.0
 * @Modified：学习统计
 * @Description:
 */
@Data
@ApiModel(value = "学习统计分页查询")
public class CountLearnPageAll extends BaseCountPageAll {

    @ApiModelProperty(name = "studentName", value = "学生名称", dataType = "string")
    private String studentName;

    @ApiModelProperty(name = "className", value = "班级名称", dataType = "string")
    private String className;

    @ApiModelProperty(name = "specialtyName", value = "专业名称", dataType = "string")
    private String specialtyName;
}