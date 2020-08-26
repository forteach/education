package com.forteach.education.statistics.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.statistics.domain.CountLearn;
import com.forteach.education.statistics.repository.CountLearnRepository;
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
 * @date: 2020/8/19 15:17
 * @version: 1.0
 * @description：学习情况
 */
@Service
@Slf4j
public class CountLearnServiceImpl implements BaseCountService<CountLearn> {

    private final CountLearnRepository countLearnRepository;

    private final BaseService baseService;

    public CountLearnServiceImpl(CountLearnRepository countLearnRepository, BaseService baseService) {
        this.countLearnRepository = countLearnRepository;
        this.baseService = baseService;
    }

    @Override
    public List<ChartCakeVo> findAllChartCake() {
        List<ChartCakeVo> list = new ArrayList<>();
        list.add(new ChartCakeVo("历史",62));
        list.add(new ChartCakeVo("数学",59));
        list.add(new ChartCakeVo("生物",92));
        list.add(new ChartCakeVo("地理",80));
        list.add(new ChartCakeVo("物理",72));
        return list;
    }

    @Override
    public List<ChartColumnarVo> findAllColumnarList() {
        ChartColumnarVo chartColumnarVo = new ChartColumnarVo("课程数（教研室）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        ChartColumnarVo chartQuestionVo = new ChartColumnarVo("练习数（教研室）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        ChartColumnarVo chartHomeworkVo = new ChartColumnarVo("作业数（教研室）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        ChartColumnarVo chartExperienceExchangeVo = new ChartColumnarVo("心得交流数（教研室）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        return CollUtil.toList(chartColumnarVo, chartQuestionVo, chartHomeworkVo, chartExperienceExchangeVo);
    }

    @Override
    public Page<CountLearn> findAllPage(Map<String, Object> map, SortVo sortVo) {
        return baseService.findAllPage(map, sortVo.getPage(), sortVo.getSize(), sortVo.getSort(),
                countLearnRepository);
    }

    public List<ChartColumnarVo> findAllColumnarBySpecialty() {
        ChartColumnarVo chartColumnarVo = new ChartColumnarVo("课程数（专业）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        ChartColumnarVo chartQuestionVo = new ChartColumnarVo("练习数（专业）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        ChartColumnarVo chartHomeworkVo = new ChartColumnarVo("作业数（专业）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        ChartColumnarVo chartExperienceExchangeVo = new ChartColumnarVo("心得交流数（专业）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        return CollUtil.toList(chartColumnarVo, chartQuestionVo, chartHomeworkVo, chartExperienceExchangeVo);
    }
}