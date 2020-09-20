package com.forteach.education.databank.web.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/4 11:15
 * @Version: v1.0
 * @Modified：行程
 * @Description:
 */
@Data
@Builder
@ApiModel(value = "行程信息")
public class PlanDateRes implements Serializable {

    @ApiModelProperty(name = "id", value = "主键id", dataType = "string")
    private String id;

    @ApiModelProperty(name = "openId", value = "微信用户openId", dataType = "string", required = true)
    private String openId;

    @ApiModelProperty(name = "content", value = "备注说明", dataType = "string")
    private String content;

    @ApiModelProperty(name = "contentDate", value = "行程说明", dataType = "string", required = true)
    private String contentDate;
}
