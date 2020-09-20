package com.forteach.education.databank.web.control;

import com.forteach.education.authority.service.TokenService;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.databank.domain.PlanCourse;
import com.forteach.education.databank.service.PlanCourseService;
import com.forteach.education.databank.web.req.PlanCourseReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/8 17:27
 * @Version: v1.0
 * @Modified：排课描述
 * @Description:
 */
@RestController
@RequestMapping(path = "/planCourse", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "排课信息", tags = {"排课信息"})
public class PlanCourseController {

    private final TokenService tokenService;
    private final PlanCourseService planCourseService;

    public PlanCourseController(PlanCourseService planCourseService, TokenService tokenService) {
        this.planCourseService = planCourseService;
        this.tokenService = tokenService;
    }

    @ApiOperation(value = "查询用户排课信息")
    @PostMapping(path = "/findMyPlanCourse")
    public WebResult findMyPlanCourse(@RequestBody PlanCourseReq req, @ApiIgnore HttpServletRequest httpServletRequest){
        String teacherId = tokenService.getTeacherId(httpServletRequest);
        List<PlanCourse> myPlanCourse = planCourseService.findMyPlanCourse(req.getYear(), req.getSemester(), teacherId);
        return WebResult.okResult(myPlanCourse);
    }
}