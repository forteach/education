package com.forteach.education.statistics.web.controller;

import com.forteach.education.authority.annotation.PassToken;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.statistics.service.BaseCountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * @auther: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:31
 * @version: 1.0
 * @description：
 */
public class BaseCountController<T> {


    @Qualifier("countCourseServiceImpl")
    @Autowired
    private BaseCountService baseCountService;

//    @UserLoginToken

    @PassToken
    @ApiOperation(value = "饼状图统计信息")
    @GetMapping("/cake")
    public WebResult findDatumList() {
        return WebResult.okResult(baseCountService.findAllChartCake());
    }
}