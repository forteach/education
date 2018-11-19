package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:30
 */
@Data
@Builder
@ApiModel(value = "分页查询对象")
public class SortVo {

    @NotNull(message = "页码不能位空")
    @ApiModelProperty(value = "分页", notes = "分页 从0开始", name = "page", example = "0", required = true)
    private int page;

    @NotNull(message = "每页数量不能为空")
    @ApiModelProperty(value = "每页数量", notes = "每页数量", name = "size", example = "20", required = true)
    private int size;

    @NotNull(message = "排序规则不能为空")
    @ApiModelProperty(value = "排序规则", notes = "依照数据库哪条字段排序 驼峰", name = "sorting", example = "cTime", required = true)
    private String sorting;
}
