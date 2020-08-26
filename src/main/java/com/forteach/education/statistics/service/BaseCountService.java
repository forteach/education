package com.forteach.education.statistics.service;

import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.statistics.vo.ChartCakeVo;
import com.forteach.education.statistics.vo.ChartColumnarVo;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:39
 * @version: 1.0
 * @description：统计信息
 */
@Service
public interface BaseCountService<T> {
    /**
     * 获取对应的饼状图表信息
     * @return
     */
    List<ChartCakeVo> findAllChartCake();

    /**
     * 查询柱状图数据
     * @return
     */
    List<ChartColumnarVo> findAllColumnarList();

    /**
     * 分页查询对应的统计信息
     * @return
     */
    Page<T> findAllPage(Map<String, Object> map, SortVo sortVo);
}