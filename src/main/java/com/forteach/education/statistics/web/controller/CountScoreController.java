package com.forteach.education.statistics.web.controller;

import com.forteach.education.statistics.domain.CountScore;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:16
 * @version: 1.0
 * @description：成绩统计
 */
@Api(value = "成绩统计信息", tags = {"成绩统计信息"})
@RestController
@RequestMapping(path = "/statistics/score", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CountScoreController extends BaseCountController<CountScore> {




}