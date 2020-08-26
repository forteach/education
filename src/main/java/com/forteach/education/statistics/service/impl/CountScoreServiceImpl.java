package com.forteach.education.statistics.service.impl;

import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.statistics.domain.CountScore;
import com.forteach.education.statistics.repository.CountScoreRepository;
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

import static cn.hutool.core.collection.CollUtil.toList;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 15:12
 * @version: 1.0
 * @description：学习成绩数据统计
 */
@Slf4j
@Service
public class CountScoreServiceImpl implements BaseCountService<CountScore> {

    private final CountScoreRepository countScoreRepository;

    private final BaseService baseService;

    public CountScoreServiceImpl(CountScoreRepository countScoreRepository, BaseService baseService) {
        this.countScoreRepository = countScoreRepository;
        this.baseService = baseService;
    }

    @Override
    public List<ChartCakeVo> findAllChartCake() {
        List<ChartCakeVo> list = new ArrayList<>();
        list.add(new ChartCakeVo("数学",79));
        list.add(new ChartCakeVo("语文",90));
        list.add(new ChartCakeVo("英语",99));
        list.add(new ChartCakeVo("生物",62.5));
        return list;
    }

    @Override
    public List<ChartColumnarVo> findAllColumnarList() {
        ChartColumnarVo chartColumnarVo = new ChartColumnarVo("及格数/优秀数（教研室）",
                toList("历史", "电子商务", "语文", "体育"),
                toList(335, 310, 234, 135),
                toList(135, 110, 134, 35));
        ChartColumnarVo columnarVo = new ChartColumnarVo("及格率/优秀率(教研室)",
                toList("历史", "电子商务", "语文", "体育"),
                toList(0.9, 0.95, 0.92, 0.94),
                toList(0.6, 0.55, 0.62, 0.44));
        return toList(chartColumnarVo, columnarVo);
    }

    @Override
    public Page<CountScore> findAllPage(Map<String, Object> map, SortVo sortVo) {
        return baseService.findAllPage(map, sortVo.getPage(), sortVo.getSize(), sortVo.getSort(),
                countScoreRepository);
    }

    public List<ChartColumnarVo> findAllBySpecialty(){
        ChartColumnarVo chartColumnarVo = new ChartColumnarVo("及格数/优秀数（专业）",
                toList("电子商务", "外贸", "会记", "物业"),
                toList(135, 110, 134, 135),
                toList(35, 40, 34, 35));
        ChartColumnarVo columnarVo = new ChartColumnarVo("及格率/优秀率(专业)",
                toList("电子商务", "外贸", "会记", "物业"),
                toList(0.9, 0.95, 0.92, 0.94),
                toList(0.4, 0.45, 0.32, 0.44));
        return toList(chartColumnarVo, columnarVo);
    }
}