package com.forteach.education.statistics.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.statistics.domain.CountCourse;
import com.forteach.education.statistics.repository.CountCourseRepository;
import com.forteach.education.statistics.service.BaseCountService;
import com.forteach.education.statistics.service.base.BaseService;
import com.forteach.education.statistics.vo.ChartCakeVo;
import com.forteach.education.statistics.vo.ChartColumnarVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:46
 * @version: 1.0
 * @description：获取对应的课程统计数据数据
 */
@Service
@Slf4j
public class CountCourseServiceImpl implements BaseCountService<CountCourse> {

    private final CountCourseRepository countCourseRepository;

    private final BaseService baseService;

    public CountCourseServiceImpl(CountCourseRepository countCourseRepository, BaseService baseService) {
        this.countCourseRepository = countCourseRepository;
        this.baseService = baseService;
    }

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
    public List<ChartColumnarVo> findAllColumnarList() {
        ChartColumnarVo chartColumnarVo = new ChartColumnarVo("", CollUtil.toList("语文", "数学", "英语", "历史", "体育"), CollUtil.toList(222, 32, 12.3, 234, 99));
        return CollUtil.toList(chartColumnarVo);
    }

    @Override
    public Page<CountCourse> findAllPage(Map<String, Object> map, SortVo sortVo) {
        return baseService.findAllPage(map, sortVo.getPage(), sortVo.getSize(), sortVo.getSort(),
                countCourseRepository);
    }
}