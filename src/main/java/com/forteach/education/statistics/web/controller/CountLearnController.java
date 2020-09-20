package com.forteach.education.statistics.web.controller;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.annotation.PassToken;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.statistics.domain.CountLearn;
import com.forteach.education.statistics.service.BaseCountService;
import com.forteach.education.statistics.service.impl.CountLearnServiceImpl;
import com.forteach.education.statistics.web.req.CountLearnPageAll;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * @date: 2020/8/19 14:15
 * @version: 1.0
 * @description：学习统计
 */
@Api(value = "学习统计信息", tags = {"学习统计信息"})
@RestController
@RequestMapping(path = "/statistics/learn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CountLearnController extends BaseCountController<CountLearn> {

    private final CountLearnServiceImpl countLearnService;
    public CountLearnController(BaseCountService<CountLearn> baseCountService, CountLearnServiceImpl countLearnService) {
        super(baseCountService);
        this.countLearnService = countLearnService;
    }

    @PassToken
    @ApiOperation(value = "分页查询学习统计信息")
    @PostMapping("/findAllPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentName", value = "学生名称", dataType = "string", paramType = "body"),
            @ApiImplicitParam(name = "className", value = "班级名称", dataType = "string", paramType = "body"),
            @ApiImplicitParam(name = "specialtyName", value = "专业名称", dataType = "string", paramType = "body")
    })
    public WebResult findAllPage(@RequestBody @Validated CountLearnPageAll pageAll, @ApiIgnore HttpServletRequest httpServletRequest){
        Map<String, Object> map = new HashMap<>(16);
        if (StrUtil.isNotBlank(pageAll.getClassName())){
            map.put("className", pageAll.getClassName());
        }
        if (StrUtil.isNotBlank(pageAll.getSpecialtyName())){
            map.put("specialtyName", pageAll.getSpecialtyName());
        }
        if (StrUtil.isNotBlank(pageAll.getStudentName())){
            map.put("studentName", pageAll.getStudentName());
        }
        return WebResult.okResult(baseCountService.findAllPage(map, pageAll.getSortVo()));
    }

    @PassToken
    @ApiOperation(value = "查询专业图表信息")
    @PostMapping("/findAllSpecialty")
    public WebResult findAllSpecialty(@ApiIgnore HttpServletRequest httpServletRequest){
        return WebResult.okResult(countLearnService.findAllColumnarBySpecialty());
    }
}