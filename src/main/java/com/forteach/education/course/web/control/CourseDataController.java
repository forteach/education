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
@RequestMapping(path = "/courseData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "课程资料挂接操作", tags = {"课程资料挂接信息"})
public class CourseDataController {

    @Resource
    private CourseDataService courseDataService;

    @ApiOperation(value = "保存挂接课程资料信息", notes = "保存挂接课程资料信息")
    @PostMapping("/save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "files", value = "多个文件列表", dataTypeClass = List.class)
    })
    public WebResult save(@Valid @ApiParam(name = "courseReq", value = "科目课程对象") @RequestBody CourseDataDatumReq req){
        int resp=courseDataService.save(req.getChapterId(),req.getFiles());
        return WebResult.okResult(String.valueOf(resp));

    }

    @ApiOperation(value = "保存资料信息", notes="{\"chapterId\":\"2c9180c067ee2be40167eeb29a7f0004\",\"courseId\":\"40288d5c67ed87b80167ed9569ed0000\",\"datumArea\":\"1\",\"datumType\":\"1\",\"files\":[{\"fileName\":\"工作汇报.docx\",\"filePath\":\"http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.docx\"}]}")
    @PostMapping("/updateAreaAndShare")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "资料编号", dataType = "string", required = true, paramType = "from"),
            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", required = true, paramType = "from"),
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "datumArea", value = "资料领域", dataType = "string", required = true, paramType = "from", example = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例"),
            @ApiImplicitParam(name = "datumName", value = "资料名称", dataType = "string", paramType = "from", required = true),
            @ApiImplicitParam(name = "kNodeId", value = "知识点id", dataType = "string", paramType = "from", example = "知识点 ‘,’ 进行分割"),
            @ApiImplicitParam(name = "datumType", value = "资料类型", dataType = "string", required = true, paramType = "from", example = "资料类型 1文档　2图册　3视频　4音频　5链接"),
            @ApiImplicitParam(name = "teachShare", value = "教师共享", dataType = "string", required = true, paramType = "from", example = "0不共享 1共享"),
            @ApiImplicitParam(name = "stuShare", value = "学生共享", dataType = "string", required = true, paramType = "from", example = "0不共享 1共享"),
    })
    public WebResult updateAreaAndShare(@Valid @ApiParam(value = "保存资料信息", name = "chapteData") @RequestBody ChapteDataReq chapteDataReq){
        //1、初始化参数
        String courseId=chapteDataReq.getCourseId();
        String chapterId=chapteDataReq.getChapterId();
        String kNodeId=chapteDataReq.getKNodeId();
        String fileId=chapteDataReq.getFileId();
        String datumArea=chapteDataReq.getDatumArea();
        String datumType=chapteDataReq.getDatumType();
        String teachShare=chapteDataReq.getTeachShare();
        String stuShare=chapteDataReq.getStuShare();
        // 2、设置返回结果
        return WebResult.okResult(courseDataService.updateAreaAndShare(courseId,chapterId,kNodeId,fileId,datumArea,datumType,teachShare,stuShare));
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
