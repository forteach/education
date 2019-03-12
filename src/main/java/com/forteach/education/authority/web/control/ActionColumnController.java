package com.forteach.education.authority.web.control;

import com.forteach.education.authority.service.ActionColumnService;
import com.forteach.education.authority.web.req.ActionColumnReq;
import com.forteach.education.common.keyword.WebResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-12 16:32
 * @version: 1.0
 * @description:
 */
@RestController
@RequestMapping(value = "/actionColumn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActionColumnController {
    private final ActionColumnService actionColumnService;

    private ActionColumnController(ActionColumnService actionColumnService){
        this.actionColumnService = actionColumnService;
    }

    @PostMapping("/saveEdit")
    @ApiOperation("修改保存栏目信息")
    public WebResult saveEdit(@RequestBody ActionColumnReq actionColumnReq){
        return WebResult.okResult(actionColumnService.editSaveActionColumn(actionColumnReq));
    }
}
