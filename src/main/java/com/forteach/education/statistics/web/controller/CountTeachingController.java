package com.forteach.education.statistics.web.controller;

import com.forteach.education.statistics.domain.CountTeaching;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:14
 * @version: 1.0
 * @description：教学统计
 */
@Api(value = "教学统计信息", tags = {"教学统计信息"})
@RestController
@RequestMapping(path = "/statistics/teaching", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CountTeachingController extends BaseCountController<CountTeaching> {


}