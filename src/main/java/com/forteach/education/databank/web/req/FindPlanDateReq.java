package com.forteach.education.databank.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/4 13:29
 * @Version: v1.0
 * @Modified：查询日程记录
 * @Description:
 */
@Data
@ApiModel(value = "查询日程记录")
public class FindPlanDateReq implements Serializable {

    @NotBlank(message = "日期不能是空")
    @ApiModelProperty(name = "contentDate", value = "日程日期", dataType = "string", required = true, example = "2020-09")
    private String contentDate;
}