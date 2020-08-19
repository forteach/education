package com.forteach.education.statistics.service.impl;

import com.forteach.education.statistics.domain.CountCourse;
import com.forteach.education.statistics.domain.CountLearn;
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
 * @date: 2020/8/19 15:17
 * @version: 1.0
 * @description：学习情况
 */
@Service
@Slf4j
public class CountLearnServiceImpl implements BaseCountService<CountLearn> {
    @Override
    public List<ChartCakeVo> findAllChartCake() {
        List<ChartCakeVo> list = new ArrayList<>();
        return list;
    }

    @Override
    public Page<CountLearn> findAllPage() {
        List<CountLearn> list = new ArrayList<>();
        return new PageImpl<>(list);
    }

}