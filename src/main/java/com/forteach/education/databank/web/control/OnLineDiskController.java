package com.forteach.education.databank.web.control;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.databank.domain.OnLineDisk;
import com.forteach.education.databank.service.OnLineDiskService;
import com.forteach.education.databank.web.req.OnLineDiskListReq;
import com.forteach.education.databank.web.req.OnLineDiskSaveReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 10:10
 * @Version: v1.0
 * @Modified：在线网盘
 * @Description:
 */
@RestController
@RequestMapping(path = "/onLineDisk", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "在线网盘", tags = {"在线网盘"})
public class OnLineDiskController {

    private final OnLineDiskService onLineDiskService;

    public OnLineDiskController(OnLineDiskService onLineDiskService) {
        this.onLineDiskService = onLineDiskService;
    }

    @ApiOperation(value = "上传文件")
    @PostMapping(path = "/save")
    public WebResult save(@RequestBody @Validated OnLineDiskSaveReq req) {

        OnLineDisk onLineDisk = new OnLineDisk();
        BeanUtil.copyProperties(req, onLineDisk);
        //获取文件扩展名
        if (StrUtil.isNotBlank(req.getFileName())) {
            String suffix = FileUtil.getSuffix(req.getFileName());
            onLineDisk.setFileSuffix(suffix);
        }
        onLineDiskService.save(onLineDisk);
        return WebResult.okResult();
    }

    @ApiOperation(value = "我的文件列表")
    @PostMapping(path = "/list")
    public WebResult list(@RequestBody @Validated OnLineDiskListReq req) {
        List<OnLineDisk> lineDiskList = onLineDiskService.list(req.getPId(), req.getUserId());
        return WebResult.okResult(lineDiskList);
    }

    @ApiOperation(value = "删除文件")
    @DeleteMapping(value = "/")
    @ApiImplicitParam(name = "ids", value = "要删除的文件列表", dataType = "set", required = true)
    public WebResult deleteList(@RequestBody @Validated @Size(message = "文件编号不存在") Set<Long> ids) {
        onLineDiskService.delete(ids);
        return WebResult.okResult();
    }


//    @ApiOperation(value = "移动文件夹")
//    @PostMapping(path = "/move")
//    public WebResult move(@RequestBody MoveOnLineDiskReq req){
//        onLineDiskService.move(Long.valueOf(req.getId()), Long.valueOf(req.getPId()));
//        return WebResult.okResult();
//    }
}