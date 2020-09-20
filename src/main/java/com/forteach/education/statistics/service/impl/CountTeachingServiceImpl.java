package com.forteach.education.statistics.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.forteach.education.common.keyword.Dic;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.statistics.domain.CountTeaching;
import com.forteach.education.statistics.repository.CountTeachingRepository;
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

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 15:17
 * @version: 1.0
 * @description：教学统计
 */
@Service
@Slf4j
public class CountTeachingServiceImpl implements BaseCountService<CountTeaching> {

    private final CountTeachingRepository countTeachingRepository;

    private final BaseService baseService;

    public CountTeachingServiceImpl(CountTeachingRepository countTeachingRepository, BaseService baseService) {
        this.countTeachingRepository = countTeachingRepository;
        this.baseService = baseService;
    }

    @Override
    public List<ChartCakeVo> findAllChartCake() {
        List<ChartCakeVo> list = new ArrayList<>(16);
        list.add(new ChartCakeVo("课程数",62));
        list.add(new ChartCakeVo("资料数",62222));
        list.add(new ChartCakeVo("题目数",23562));
        list.add(new ChartCakeVo("习题数",12162));
        return list;
    }

    @Override
    public List<ChartColumnarVo> findAllColumnarList() {
        List<CountTeaching> all = countTeachingRepository.findAllByIsValidatedEquals(Dic.TAKE_EFFECT_OPEN);
        List<String> courseNames = all.stream().map(CountTeaching::getCourseName).collect(Collectors.toList());
        List courseList = all.stream().map(CountTeaching::getCourseNum).collect(Collectors.toList());
        List dataList = all.stream().map(CountTeaching::getDataNum).collect(Collectors.toList());
        List questionList = all.stream().map(CountTeaching::getQuestionNum).collect(Collectors.toList());
        List chapterList = all.stream().map(CountTeaching::getChapterNum).collect(Collectors.toList());
                ChartColumnarVo chartColumnarVo = new ChartColumnarVo("课程数（教研室）",
                CollUtil.newArrayList(courseNames),
                CollUtil.newArrayList(courseList));
        ChartColumnarVo columnarChapterVo = new ChartColumnarVo("章节数（教研室）",
                CollUtil.newArrayList(courseNames),
                CollUtil.newArrayList(chapterList));
        ChartColumnarVo columnarDataVo = new ChartColumnarVo("资料数（教研室）",
                CollUtil.newArrayList(courseNames),
                CollUtil.newArrayList(dataList));
        ChartColumnarVo columnarQuestionVo = new ChartColumnarVo("题目数（教研室）",
                CollUtil.newArrayList(courseNames),
                CollUtil.newArrayList(questionList));
        return CollUtil.toList(chartColumnarVo, columnarChapterVo, columnarDataVo, columnarQuestionVo);
    }

    @Override
    public Page<CountTeaching> findAllPage(Map<String, Object> map, SortVo sortVo) {
        return baseService.findAllPage(map, sortVo.getPage(), sortVo.getSize(), sortVo.getSort(),
                countTeachingRepository);
    }
}