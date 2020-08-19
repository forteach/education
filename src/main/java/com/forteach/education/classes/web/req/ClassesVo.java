package com.forteach.education.classes.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-11 11:20
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "修改班级信息")
public class ClassesVo implements Serializable {

    @ApiModelProperty(name = "classId", value = "班级编号", dataType = "string", required = true)
    private String classId;

    @ApiModelProperty(name = "className", value = "班级", dataType = "string")
    private String className;

    @ApiModelProperty(name = "grade", value = "年级", dataType = "int")
    private Integer grade;
}
