package com.forteach.education.statistics.web.controller;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.annotation.PassToken;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.statistics.domain.CountScore;
import com.forteach.education.statistics.service.BaseCountService;
import com.forteach.education.statistics.service.impl.CountScoreServiceImpl;
import com.forteach.education.statistics.web.req.CountScorePageAll;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date: 2020/8/19 14:16
 * @version: 1.0
 * @description：成绩统计
 */
@Api(value = "成绩统计信息", tags = {"成绩统计信息"})
@RestController
@RequestMapping(path = "/statistics/score", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CountScoreController extends BaseCountController<CountScore> {

    private final CountScoreServiceImpl countScoreService;

    public CountScoreController(BaseCountService<CountScore> baseCountService, CountScoreServiceImpl countScoreService) {
        super(baseCountService);
        this.countScoreService = countScoreService;
    }

    @PassToken
    @ApiOperation(value = "学生学习成绩统计")
    @PostMapping("/findAllPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "studentName", value = "学生名称", dataType = "string", paramType = "body"),
            @ApiImplicitParam(name = "className", value = "班级名称", dataType = "string", paramType = "body"),
            @ApiImplicitParam(name = "specialtyName", value = "专业名称", dataType = "string", paramType = "body")
    })
    public WebResult findAllPage(@RequestBody @Validated CountScorePageAll pageAll, @ApiIgnore HttpServletRequest httpServletRequest){
        Map<String, Object> map = new HashMap<>(8);
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
    @PostMapping(path = "/findAllBySpecialty")
    @ApiOperation(value = "根据专业查询图表信息")
    public WebResult findAllSpecialty(@ApiIgnore HttpServletRequest httpServletRequest){
        return WebResult.okResult(countScoreService.findAllBySpecialty(""));
    }

}