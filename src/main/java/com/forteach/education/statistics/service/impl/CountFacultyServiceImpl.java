package com.forteach.education.statistics.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.statistics.domain.CountFaculty;
import com.forteach.education.statistics.repository.CountFacultyRepository;
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
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/21 14:03
 * @Version: v1.0
 * @Modified：学习情况
 * @Description:
 */
@Service
@Slf4j
public class CountFacultyServiceImpl implements BaseCountService<CountFaculty> {

    private final CountFacultyRepository countFacultyRepository;

    private final BaseService baseService;

    public CountFacultyServiceImpl(CountFacultyRepository countFacultyRepository, BaseService baseService) {
        this.countFacultyRepository = countFacultyRepository;
        this.baseService = baseService;
    }

    @Override
    public List<ChartCakeVo> findAllChartCake() {
        List<ChartCakeVo> list = new ArrayList<>();
        list.add(new ChartCakeVo("英语教研室", 12));
        list.add(new ChartCakeVo("数学教研室", 6));
        list.add(new ChartCakeVo("信息技术教研室", 12));
        list.add(new ChartCakeVo("体育教研室", 12));
        return list;
    }

    @Override
    public List<ChartColumnarVo> findAllColumnarList() {
        ChartColumnarVo chartColumnarVo = new ChartColumnarVo("",
                CollUtil.toList("电工电子", "美容美发", "英语", "历史", "体育", "电子商务"),
                CollUtil.toList(2, 44, 66, 23.9, 33, 72));
        return CollUtil.toList(chartColumnarVo);
    }

    @Override
    public Page<CountFaculty> findAllPage(Map<String, Object> map, SortVo sortVo) {
        return baseService.findAllPage(map, sortVo.getPage(), sortVo.getSize(), sortVo.getSort(),
                countFacultyRepository);
    }
}
