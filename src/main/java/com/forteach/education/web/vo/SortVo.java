package com.forteach.education.web.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@AllArgsConstructor
public class SortVo {

    @NotNull(message = "页码不能位空")
    @ApiModelProperty(value = "分页", notes = "分页 从0开始", dataType = "int", name = "page", example = "0", required = true)
    private int page;

    @NotNull(message = "每页数量不能为空")
    @ApiModelProperty(value = "每页数量", notes = "每页数量", dataType = "int", name = "size", example = "20", required = true)
    private int size;

    @NotNull(message = "排序规则不能为空")
    @ApiModelProperty(value = "排序规则", notes = "依照数据库哪条字段排序 驼峰", dataType = "string", name = "sorting", example = "cTime", required = true)
    private String sorting;

    @ApiModelProperty(value = "有无效", name = "isValidated",notes = "0 有效 1　无效 (默认０)", dataType = "string",example = "0")
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
    private String isValidated;

    @NotNull(message = "排序方式不能为空")
    @ApiModelProperty(value = "sort", name = "排序方式" ,notes = "排序方式 ASC 正序　DESC 倒叙　默认倒叙", dataType = "string", example = "DESC")
    private String sort;
}
