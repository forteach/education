package com.forteach.education.statistics.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:41
 * @version: 1.0
 * @description：饼状图数据
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChartCakeVo {
    /**
     * 对应的名称
     */
    private String name;
    /**
     * 对应的数据
     */
    private Number value;
}