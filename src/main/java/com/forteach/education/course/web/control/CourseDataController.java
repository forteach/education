package com.forteach.education.course.web.control;

import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.course.service.CourseDataService;
import com.forteach.education.course.web.req.CoursewareAll;
import com.forteach.education.databank.web.req.ChapteDataListReq;
import com.forteach.education.databank.web.req.ChapteDataReq;
import com.forteach.education.databank.web.res.DatumResp;
import com.forteach.education.web.req.CourseDataDatumReq;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 16:52
 * @Version: 1.0
 * @Description:　科目课程资料挂接信息
 */
@Slf4j
@RestController
@RequestMapping(path = "/courseware", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "课程资料挂接操作", tags = {"课程资料挂接信息"})
public class CourseDataController {

    @Resource
    private CourseDataService courseDataService;

    @ApiOperation(value = "保存挂接课程资料信息", notes = "保存挂接课程资料信息")
    @PostMapping("/save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "importantType", value = "1、教案 2、课件", dataTypeClass = String.class),
            @ApiImplicitParam(name = "datumType", value = "1、文件 3、视频", dataTypeClass = String.class),
            @ApiImplicitParam(name = "files", value = "图集文件url", dataTypeClass = CoursewareAll.class)
    })
    public WebResult save(@Valid @ApiParam(name = "courseReq", value = "科目课程对象") @RequestBody CourseDataDatumReq req){
        int resp=courseDataService.save(req);
        return WebResult.okResult(String.valueOf(resp));

    }

    @ApiOperation(value = "获得挂接课件资料列表", notes = "获得挂接课件资料列表")
    @PostMapping("/findDatumList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "photoDatumName", value = "图集名称", dataTypeClass = String.class),

            @ApiImplicitParam(name = "files", value = "图集文件url", dataTypeClass = CoursewareAll.class)
    })
    public WebResult findDatumList(@Valid @ApiParam(name = "courseReq", value = "科目课程对象") @RequestBody ChapteDataListReq req){
        SortVo sortVo=req.getSortVo();
        PageRequest pageReq=PageRequest.of(sortVo.getPage(), sortVo.getSize());
        List<DatumResp> list=courseDataService.findDatumList(req.getChapterId(),req.getDatumType(),pageReq);
        return WebResult.okResult(list);

    }


}
