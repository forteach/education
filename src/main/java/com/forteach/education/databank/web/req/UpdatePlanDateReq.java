package com.forteach.education.databank.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/4 13:13
 * @Version: v1.0
 * @Modified：更新行程信息
 * @Description:
 */
@Data
@ApiModel(value = "添加跟新信息")
public class UpdatePlanDateReq implements Serializable {

    @ApiModelProperty(name = "id", value = "记录id", dataType = "string", example = "ff8081817278a8be0172790566320002")
    private String id;

    @ApiModelProperty(name = "content", value = "备注说明", dataType = "string", example = "10:00-12:00 电子商务一班数学")
    private String content;

    @NotBlank(message = "日程信息不能为空")
    @ApiModelProperty(name = "contentDate", value = "行程说明", dataType = "string", required = true, example = "2020-09-04")
    private String contentDate;
}
