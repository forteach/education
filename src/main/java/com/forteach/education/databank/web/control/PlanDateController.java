package com.forteach.education.databank.web.control;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.databank.domain.PlanDate;
import com.forteach.education.databank.service.PlanDateService;
import com.forteach.education.databank.web.req.FindPlanDateReq;
import com.forteach.education.databank.web.req.UpdatePlanDateReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/4 13:11
 * @Version: v1.0
 * @Modified：行程日期
 * @Description:
 */
@RestController
@RequestMapping(path = "/planDate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "行程日期信息", tags = {"行程日期信息"})
public class PlanDateController {

    private final PlanDateService planDateService;

    private final TokenService tokenService;

    public PlanDateController(PlanDateService planDateService, TokenService tokenService) {
        this.planDateService = planDateService;
        this.tokenService = tokenService;
    }

    @ApiOperation(value = "添加更新日程信息")
    @PostMapping("/update")
    public WebResult update(@RequestBody @Validated UpdatePlanDateReq res, @ApiIgnore HttpServletRequest httpServletRequest) {
        PlanDate planDate = new PlanDate();
        BeanUtil.copyProperties(res, planDate);
        String openId = tokenService.getOpenId(httpServletRequest);
        if (StrUtil.isBlank(res.getId())) {
            planDate.setId(IdUtil.fastSimpleUUID());
        }
        planDate.setOpenId(openId);
        planDateService.saveUpdate(planDate);
        return WebResult.okResult();
    }

    @ApiOperation(value = "查询用户的日程记录")
    @PostMapping(value = "/findPlanDate")
    public WebResult findPlanDate(@RequestBody @Validated FindPlanDateReq req, @ApiIgnore HttpServletRequest httpServletRequest) {
        return WebResult.okResult(planDateService.findByOpenIdAndPlanDate(tokenService.getOpenId(httpServletRequest), req.getContentDate()));
    }

    @DeleteMapping(path = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", dataType = "string", paramType = "path", required = true)
    })
    public WebResult deleteById(@PathVariable @Validated @NotBlank(message = "id is bank") String id){
        planDateService.deleteById(id);
        return WebResult.okResult();
    }
}