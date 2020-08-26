package com.forteach.education.statistics.web.controller;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.annotation.PassToken;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.statistics.domain.CountFaculty;
import com.forteach.education.statistics.service.BaseCountService;
import com.forteach.education.statistics.web.req.CountFacultyPageAll;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:18
 * @version: 1.0
 * @description：院系统计
 */
@Api(value = "院系统计信息", tags = {"院系统计信息"})
@RestController
@RequestMapping(path = "/statistics/faculty", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CountFacultyController extends BaseCountController<CountFaculty> {


    public CountFacultyController(BaseCountService<CountFaculty> baseCountService) {
        super(baseCountService);
    }

    @PassToken
    @ApiOperation(value = "院系统计信息")
    @PostMapping("/findAllPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherName", value = "教师名称", dataType = "string", paramType = "body"),
            @ApiImplicitParam(name = "teachingOfficeName", value = "教研室名称", dataType = "string", paramType = "body")
    })
    public WebResult findAllPage(@RequestBody @Validated CountFacultyPageAll pageAll, HttpServletRequest httpServletRequest){
        Map<String, Object> map = new HashMap<>(4);
        if (StrUtil.isNotBlank(pageAll.getTeachingOfficeName())){
            map.put("teachingOfficeName", pageAll.getTeachingOfficeName());
        }
        if (StrUtil.isNotBlank(pageAll.getTeacherName())){
            map.put("teacherName", pageAll.getTeacherName());
        }
        return WebResult.okResult(baseCountService.findAllPage(map, pageAll.getSortVo()));
    }
}