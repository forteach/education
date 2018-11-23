package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

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

    @Range(min = 0, max = 1, message = "是否有效参数不正确")
    @ApiModelProperty(value = "有无效", name = "isValidated",notes = "0 有效 1　无效 (默认0)", dataType = "string",example = "0")
    private String isValidated;

    @NotNull(message = "排序方式不能为空")
    @Range(min = 0, max = 1, message = "排序方式不正确")
    @ApiModelProperty(value = "sort", name = "排序方式" ,notes = "排序方式 0 正序　１ 倒叙　默认倒叙(1)", dataType = "int", example = "1")
    private int sort = 1;
}
