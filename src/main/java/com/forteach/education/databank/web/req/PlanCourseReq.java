package com.forteach.education.databank.web.req;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/8 17:21
 * @Version: v1.0
 * @Modified：查询课程表信息
 * @Description:
 */
@Data
@ApiModel(value = "查看课程表信息")
public class PlanCourseReq implements Serializable {

    @ApiModelProperty(name = "year", value = "年份", dataType = "string")
    private String year = StrUtil.isBlank(this.getYear()) ? String.valueOf(DateUtil.thisYear()) : this.getYear();

    @ApiModelProperty(name = "semester", value = "学期", dataType = "string")
    private String semester = StrUtil.isBlank(this.getSemester()) ? String.valueOf(DateUtils.getSemesterByNow()) : this.getSemester();
}