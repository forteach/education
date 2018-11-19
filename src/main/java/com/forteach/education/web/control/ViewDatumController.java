package com.forteach.education.web.control;

import com.forteach.education.common.WebResult;
import com.forteach.education.domain.ViewDatum;
import com.forteach.education.service.ViewDatumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/19 21:30
 * @Version: 1.0
 * @Description: 视频资料操作
 */
@RestController
@RequestMapping("/viewDatum")
@Api(value = "视频资料操作", tags = {"视频资源接口"})
public class ViewDatumController {

    @Autowired
    private ViewDatumService viewDatumService;

    @ApiOperation(value = "保存视频", notes = "保存视频资源链接信息")
    @PostMapping("/save")
    public WebResult save(@RequestBody ViewDatum viewDatum){
        return WebResult.okResult(viewDatumService.save(viewDatum));
    }
}
