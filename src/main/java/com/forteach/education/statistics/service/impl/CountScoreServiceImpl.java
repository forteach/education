package com.forteach.education.statistics.service.impl;

import com.forteach.education.statistics.domain.CountLearn;
import com.forteach.education.statistics.domain.CountScore;
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
 * @date: 2020/8/19 15:12
 * @version: 1.0
 * @description：学习成绩数据统计
 */
@Slf4j
@Service
public class CountScoreServiceImpl implements BaseCountService<CountScore> {


    @Override
    public List<ChartCakeVo> findAllChartCake() {
        List<ChartCakeVo> list = new ArrayList<>();
        return list;
    }

    @Override
    public Page<CountScore> findAllPage() {
        List<CountScore> list = new ArrayList<>();
        return new PageImpl<>(list);
    }
}