package com.forteach.education.statistics.web.controller;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.annotation.PassToken;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.statistics.domain.CountCourse;
import com.forteach.education.statistics.service.BaseCountService;
import com.forteach.education.statistics.web.req.CountCoursePageAll;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:17
 * @version: 1.0
 * @description：课程统计
 */
@Api(value = "课程统计信息", tags = {"课程统计信息"})
@RestController
@RequestMapping(value = "/statistics/course", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CountCourseController extends BaseCountController<CountCourse> {

    public CountCourseController(BaseCountService<CountCourse> baseCountService) {
        super(baseCountService);
    }

    @PassToken
    @ApiOperation(value = "课程统计信息分页查询")
    @PostMapping("/findAllPage")
    @ApiImplicitParam(name = "courseName", value = "课程名称", dataType = "string", paramType = "body")
    public WebResult findAllPage(@RequestBody @Validated CountCoursePageAll pageAll, @ApiIgnore HttpServletRequest httpServletRequest){
        Map<String, Object> map = new HashMap<>(2);
        if (StrUtil.isNotBlank(pageAll.getCourseName())){
            map.put("courseName", pageAll.getCourseName());
        }
        return WebResult.okResult(baseCountService.findAllPage(map, pageAll.getSortVo()));
    }

}