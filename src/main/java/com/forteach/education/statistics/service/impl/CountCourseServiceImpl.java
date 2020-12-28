package com.forteach.education.statistics.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.forteach.education.common.keyword.Dic;
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
        list.add(new ChartCakeVo("课程数", 62));
        list.add(new ChartCakeVo("题目数", 764));
        list.add(new ChartCakeVo("资料数", 764));
        list.add(new ChartCakeVo("章节数", 74));
        return list;
    }

    @Override
    public List<ChartColumnarVo> findAllColumnarList() {
        List<CountCourse> all = countCourseRepository.findAllByIsValidatedEquals(Dic.TAKE_EFFECT_OPEN);
        long teacherNum = all.stream().map(CountCourse::getTeacherId).distinct().count();
        int dataNum = all.stream().mapToInt(CountCourse::getDataNum).sum();
        int questionNum = all.stream().mapToInt(CountCourse::getQuestionNum).sum();
        int chapterNum = all.stream().mapToInt(CountCourse::getChapterNum).sum();
        ChartColumnarVo chartColumnarVo = new ChartColumnarVo("",
                CollUtil.toList("教师数量", "章节数量", "资料数", "题目数"),
                CollUtil.toList(teacherNum, chapterNum, dataNum, questionNum));
        return CollUtil.toList(chartColumnarVo);
    }

    @Override
    public Page<CountCourse> findAllPage(Map<String, Object> map, SortVo sortVo) {
        return baseService.findAllPage(map, sortVo.getPage(), sortVo.getSize(), sortVo.getSort(),
                countCourseRepository);
    }


    public void saveCourse() {

    }
}