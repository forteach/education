package com.forteach.education.statistics.service;

import com.forteach.education.statistics.vo.ChartCakeVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @auther: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:39
 * @version: 1.0
 * @description：统计信息
 */
public interface BaseCountService<T> {
    /**
     * 获取对应的饼状图表信息
     * @return
     */
    List<ChartCakeVo> findAllChartCake();

    /**
     * 分页查询对应的统计信息
     * @return
     */
    Page<T> findAllPage();
}