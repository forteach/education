package com.forteach.education.statistics.web.controller;

import com.forteach.education.statistics.domain.CountCourse;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:17
 * @version: 1.0
 * @description：课程统计
 */
@Api(value = "课程统计信息", tags = {"课程统计信息"})
@RestController
@RequestMapping(value = "/statistics/course", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CountCourseController extends BaseCountController<CountCourse> {


}