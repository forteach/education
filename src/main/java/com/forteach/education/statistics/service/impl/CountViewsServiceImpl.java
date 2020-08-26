package com.forteach.education.statistics.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.statistics.domain.CountTeaching;
import com.forteach.education.statistics.domain.CountViews;
import com.forteach.education.statistics.repository.CountViewsRepository;
import com.forteach.education.statistics.service.BaseCountService;
import com.forteach.education.statistics.service.base.BaseService;
import com.forteach.education.statistics.vo.ChartCakeVo;
import com.forteach.education.statistics.vo.ChartColumnarVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/25 15:25
 * @Version: v1.0
 * @Modified：访问量统计
 * @Description:
 */
@Service
@Slf4j
public class CountViewsServiceImpl implements BaseCountService<CountViews> {
    private final BaseService baseService;
    private final CountViewsRepository countViewsRepository;

    public CountViewsServiceImpl(CountViewsRepository countViewsRepository, BaseService baseService) {
        this.countViewsRepository = countViewsRepository;
        this.baseService = baseService;
    }

    @Override
    public List<ChartCakeVo> findAllChartCake() {
        List<ChartCakeVo> list = new ArrayList<>();
        list.add(new ChartCakeVo("课程数",62));
        list.add(new ChartCakeVo("资料数",62222));
        list.add(new ChartCakeVo("题目数",23562));
        list.add(new ChartCakeVo("习题数",12162));
        return list;
    }

    @Override
    public List<ChartColumnarVo> findAllColumnarList() {
        ChartColumnarVo chartColumnarCourseVo = new ChartColumnarVo("课程访问量（教研室）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        ChartColumnarVo chartColumnarDataVo = new ChartColumnarVo("资料访问量（教研室）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        ChartColumnarVo chartColumnarQuestionVo = new ChartColumnarVo("题目访问量（教研室）",
                CollUtil.toList("历史", "电子商务", "语文", "体育"),
                CollUtil.toList(335, 310, 234, 135));
        List<ChartColumnarVo> list = CollUtil.toList(chartColumnarCourseVo, chartColumnarDataVo, chartColumnarQuestionVo);
        return list;
    }

    @Override
    public Page<CountViews> findAllPage(Map<String, Object> map, SortVo sortVo) {
        return baseService.findAllPage(map, sortVo.getPage(), sortVo.getSize(), sortVo.getSort(),
                countViewsRepository);
    }

    /**
     * 查询访问量统计
     * @return
     */
    public ChartColumnarVo findAllViews(){
        return new ChartColumnarVo("访问量概况",
                CollUtil.toList("课程", "资料", "题目"),
                CollUtil.toList(1335, 3310, 18234));
    }
}
