package com.forteach.education.statistics.service.impl;

import com.forteach.education.statistics.domain.CountCourse;
import com.forteach.education.statistics.service.BaseCountService;
import com.forteach.education.statistics.vo.ChartCakeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:46
 * @version: 1.0
 * @description：获取对应的课程统计数据数据
 */
@Service
@Slf4j
public class CountCourseServiceImpl implements BaseCountService<CountCourse> {
    @Override
    public List<ChartCakeVo> findAllChartCake() {
        List<ChartCakeVo> list = new ArrayList<>();
        list.add(new ChartCakeVo("课程数",62));
        list.add(new ChartCakeVo("题目数",12764));
        list.add(new ChartCakeVo("资料数",72764));
        list.add(new ChartCakeVo("章节数",7764));
        return list;
    }

    @Override
    public Page<CountCourse> findAllPage() {
        List<CountCourse> list = new ArrayList<>();
        return new PageImpl<>(list);
    }
}