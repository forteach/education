package com.forteach.education.statistics.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/21 13:58
 * @Version: v1.0
 * @Modified：教学情况分页查询
 * @Description:
 */
@Data
@ApiModel(value = "分页查询教学情况")
public class CountTeachingPageAll extends BaseCountPageAll {

    @ApiModelProperty(name = "teacherName", value = "教师名称", dataType = "string")
    private String teacherName;

    @ApiModelProperty(name = "teachingOfficeName", value = "教研室名称", dataType = "string")
    private String teachingOfficeName;
}