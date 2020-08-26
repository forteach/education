package com.forteach.education.statistics.web.controller;

import com.forteach.education.authority.annotation.PassToken;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.statistics.service.BaseCountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:31
 * @version: 1.0
 * @description：
 */
@Component
public abstract class BaseCountController<T> {


    public final BaseCountService<T> baseCountService;

    @Autowired
    public BaseCountController(BaseCountService<T> baseCountService) {
        this.baseCountService = baseCountService;
    }

    @PassToken
    @ApiOperation(value = "饼状图统计信息")
    @GetMapping("/cake")
    public WebResult findCakeList(HttpServletRequest httpServletRequest) {
        return WebResult.okResult(baseCountService.findAllChartCake());
    }


    @PassToken
    @ApiOperation(value = "查询柱状图(教研室)")
    @GetMapping("/columnar")
    public WebResult findColumnarList(HttpServletRequest httpServletRequest){
        return WebResult.okResult(baseCountService.findAllColumnarList());
    }
}