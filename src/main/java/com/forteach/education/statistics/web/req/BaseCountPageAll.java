package com.forteach.education.statistics.web.req;

import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 20:39
 * @version: 1.0
 * @description：分页统计查询
 */
@Data
public class BaseCountPageAll implements Serializable {

    @ApiModelProperty(value = "分页排序字段", name = "sortVo")
    private SortVo sortVo = new SortVo();
}