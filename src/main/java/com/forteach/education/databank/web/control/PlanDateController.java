package com.forteach.education.databank.web.control;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.annotation.PassToken;
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

    public PlanDateController(PlanDateService planDateService) {
        this.planDateService = planDateService;
    }

    @PassToken
    @ApiOperation(value = "添加更新日程信息")
    @PostMapping("/update")
    public WebResult update(@RequestBody @Validated UpdatePlanDateReq res) {
        PlanDate planDate = new PlanDate();
        BeanUtil.copyProperties(res, planDate);
        if (StrUtil.isBlank(res.getId())) {
            planDate.setId(IdUtil.fastSimpleUUID());
        }
        planDateService.saveUpdate(planDate);
        return WebResult.okResult();
    }

    @PassToken
    @ApiOperation(value = "查询用户的日程记录")
    @PostMapping(value = "/findPlanDate")
    public WebResult findPlanDate(@RequestBody @Validated FindPlanDateReq req) {
        return WebResult.okResult(planDateService.findByOpenIdAndPlanDate(req.getOpenId(), req.getContentDate()));
    }

    @PassToken
    @DeleteMapping(path = "/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键id", dataType = "string", paramType = "path", required = true)
    })
    public WebResult deleteById(@PathVariable @Validated @NotBlank(message = "id is bank") String id){
        planDateService.deleteById(id);
        return WebResult.okResult();
    }
}