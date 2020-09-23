package com.forteach.education.statistics.service.impl;

import com.forteach.education.common.keyword.Dic;
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
import java.util.stream.Collectors;

import static cn.hutool.core.collection.CollUtil.newArrayList;
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
        list.add(new ChartCakeVo("数学", 79));
        list.add(new ChartCakeVo("语文", 90));
        list.add(new ChartCakeVo("英语", 99));
        list.add(new ChartCakeVo("生物", 62.5));
        return list;
    }

    @Override
    public List<ChartColumnarVo> findAllColumnarList() {
        return findChartColumnarVo(countScoreRepository.findAllByIsValidatedEquals(Dic.TAKE_EFFECT_OPEN));
    }

    @Override
    public Page<CountScore> findAllPage(Map<String, Object> map, SortVo sortVo) {
        return baseService.findAllPage(map, sortVo.getPage(), sortVo.getSize(), sortVo.getSort(),
                countScoreRepository);
    }

    public List<ChartColumnarVo> findAllBySpecialty(String specialtyId) {
        List<CountScore> all = countScoreRepository.findAllByIsValidatedEqualsAndSpecialtyId(Dic.TAKE_EFFECT_OPEN, specialtyId);
        return findChartColumnarVo(all);
    }

    private List<ChartColumnarVo> findChartColumnarVo(List<CountScore> all) {
        //课程名称
        List<String> courseNames = all.stream().map(CountScore::getCourseName).collect(Collectors.toList());
        //优秀数
        List outstandingNum = all.stream().map(CountScore::getOutstandingNum).collect(Collectors.toList());
        //及格数
        List passNum = all.stream().map(CountScore::getPassNum).collect(Collectors.toList());
        //及格率
        List passRate = all.stream().map(CountScore::getPassRate).collect(Collectors.toList());
        //优秀率
        List outstanding = all.stream().map(CountScore::getOutstandingRate).collect(Collectors.toList());
        ChartColumnarVo chartColumnarVo = new ChartColumnarVo("及格数/优秀数（专业）",
                newArrayList(courseNames),
                newArrayList(outstandingNum),
                newArrayList(passNum));
        ChartColumnarVo columnarVo = new ChartColumnarVo("及格率/优秀率(专业)",
                newArrayList(courseNames),
                newArrayList(outstanding),
                newArrayList(passRate));
        return toList(chartColumnarVo, columnarVo);
    }
}