package com.forteach.education.databank.web.control;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.authority.annotation.UserLoginToken;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.common.web.vo.SortVo;
import com.forteach.education.databank.service.ChapteDataService;
import com.forteach.education.databank.web.control.verify.ChapterDataVerify;
import com.forteach.education.databank.web.req.ChapteDataListReq;
import com.forteach.education.databank.web.req.ChapteDataReq;
import com.forteach.education.databank.web.req.ChapterDataRemoveReq;
import com.forteach.education.web.vo.DataDatumVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-26 15:43
 * @Version: 1.0
 * @Description: 章节资料库资料操作
 */
@RestController
@RequestMapping(path = "/chapteData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "章节资料", tags = {"章节资料操作信息"})
public class ChapteDataController {

    private final ChapteDataService chapteDataService;

    private final ChapterDataVerify chapterDataVerify;

    @Autowired
    public ChapteDataController(ChapteDataService chapteDataService, ChapterDataVerify chapterDataVerify) {
        this.chapteDataService = chapteDataService;
        this.chapterDataVerify = chapterDataVerify;
    }

    @UserLoginToken
    @ApiOperation(value = "保存资料信息", notes = "{\"chapterId\":\"2c9180c067ee2be40167eeb29a7f0004\",\"courseId\":\"40288d5c67ed87b80167ed9569ed0000\",\"datumArea\":\"1\",\"datumType\":\"1\",\"files\":[{\"fileName\":\"工作汇报.docx\",\"fileUrl\":\"http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.docx\"}]}")
    @PostMapping("/save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", required = true, paramType = "form"),
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "datumArea", value = "资料领域", dataType = "string", required = true, paramType = "form", example = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例"),
            @ApiImplicitParam(name = "datumName", value = "资料名称", dataType = "string", paramType = "form", required = true),
            @ApiImplicitParam(name = "datumType", value = "资料类型", dataType = "string", required = true, paramType = "form", example = "资料类型 1文档　2图册　3视频　4音频　5链接"),
            @ApiImplicitParam(name = "teachShare", value = "教师共享", dataType = "string", required = true, paramType = "form", example = "0不共享 1共享"),
            @ApiImplicitParam(name = "stuShare", value = "学生共享", dataType = "string", required = true, paramType = "form", example = "0不共享 1共享"),
            @ApiImplicitParam(name = "files", value = "文件对象", dataTypeClass = DataDatumVo.class, paramType = "form", required = true)
    })
    public WebResult save(@ApiParam(value = "保存资料信息", name = "chapteData") @RequestBody ChapteDataReq chapteDataReq) {
        chapterDataVerify.saveVerify(chapteDataReq);
        //1、初始化参数
        String courseId = chapteDataReq.getCourseId();
        String chapterId = chapteDataReq.getChapterId();
        String datumArea = chapteDataReq.getDatumArea();
        String datumType = chapteDataReq.getDatumType();
        String teachShare = chapteDataReq.getTeachShare();
        String stuShare = chapteDataReq.getStuShare();
        List<DataDatumVo> files = chapteDataReq.getFiles();
        // 2、设置返回结果
        return WebResult.okResult(chapteDataService.save(courseId, chapterId, datumArea, datumType, teachShare, stuShare, files));
    }

    @UserLoginToken
    @ApiOperation(value = "修改课程资料共享属性信息", notes = "{\"chapterId\":\"2c9180c067ee2be40167eeb29a7f0004\",\"courseId\":\"40288d5c67ed87b80167ed9569ed0000\",\"datumArea\":\"1\",\"datumType\":\"1\",\"files\":[{\"fileName\":\"工作汇报.docx\",\"fileUrl\":\"http://118.24.120.43:8080/group1/M00/00/02/rBsADFwF5TuAKbfUAAKjQx3o88406.docx\"}]}")
    @PostMapping("/updateAreaAndShare")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "资料编号", dataType = "string", required = true, paramType = "form"),
            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", required = true, paramType = "form"),
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "datumArea", value = "资料领域", dataType = "string", required = true, paramType = "form", example = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例"),
            @ApiImplicitParam(name = "datumName", value = "资料名称", dataType = "string", paramType = "form", required = true),
            @ApiImplicitParam(name = "kNodeId", value = "知识点id", dataType = "string", paramType = "form", example = "知识点 ‘,’ 进行分割"),
            @ApiImplicitParam(name = "datumType", value = "资料类型", dataType = "string", required = true, paramType = "form", example = "资料类型 1文档　2图册　3视频　4音频　5链接"),
            @ApiImplicitParam(name = "teachShare", value = "教师共享", dataType = "string", required = true, paramType = "form", example = "0不共享 1共享"),
            @ApiImplicitParam(name = "stuShare", value = "学生共享", dataType = "string", required = true, paramType = "form", example = "0不共享 1共享"),
    })
    public WebResult updateAreaAndShare(@ApiParam(value = "保存资料信息", name = "chapteData") @RequestBody ChapteDataReq chapteDataReq) {
        chapterDataVerify.updateVerify(chapteDataReq);
        //1、初始化参数
        String courseId = chapteDataReq.getCourseId();
        String chapterId = chapteDataReq.getChapterId();
        String kNodeId = chapteDataReq.getKNodeId();
        String fileId = chapteDataReq.getFileId();
        String datumArea = chapteDataReq.getDatumArea();
        String datumType = chapteDataReq.getDatumType();
        String teachShare = chapteDataReq.getTeachShare();
        String stuShare = chapteDataReq.getStuShare();
        // 2、设置返回结果
        return WebResult.okResult(chapteDataService.updateAreaAndShare(courseId, chapterId, kNodeId, fileId, datumType, datumArea, teachShare, stuShare));
    }

    @UserLoginToken
    @ApiOperation(value = "资料信息列表", notes = "{\"chapterId\":\"2c9180c067ee2be40167eeb29a7f0004\",\"courseId\":\"2c91808d678e620701679bfccf570000\",\"datumArea\":\"1\",\"sortVo\":{\"isValidated\":\"0\",\"page\":0,\"size\":15,\"sort\":1}}")
    @PostMapping("/findDatumList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "datumArea", value = "资料领域", dataType = "string", required = true, paramType = "form", example = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例"),
            @ApiImplicitParam(name = "kNodeId", value = "知识点标签", dataType = "string", paramType = "form", example = "知识点 ‘,’ 进行分割"),
            @ApiImplicitParam(name = "datumType", value = "资料类型", dataType = "string", required = true, paramType = "form", example = "资料类型 1文档　2图册　3视频　4音频　5链接"),
            @ApiImplicitParam(name = "sortVo", value = "分页排序对象", dataTypeClass = SortVo.class, required = true)
    })
    public WebResult findDatumList(@ApiParam(value = "资料信息列表", name = "chapteData") @RequestBody ChapteDataListReq req) {
        MyAssert.blank(String.valueOf(req.getSortVo().getPage()), DefineCode.ERR0010, "页码参数不为空");
        MyAssert.blank(String.valueOf(req.getSortVo().getSize()), DefineCode.ERR0010, "每页条数不为空");
        MyAssert.gt(req.getSortVo().getPage(), 0, DefineCode.ERR0010,"页码参数不正确");
        MyAssert.gt(req.getSortVo().getSize(), 0, DefineCode.ERR0010,"每页显示条数不正确");
        //1、初始化分页参数
        SortVo sortVo = req.getSortVo();
        PageRequest pageReq = PageRequest.of(sortVo.getPage(), sortVo.getSize());
        //判断是否按资源领域查询列表，并设置返回结果
        if (StrUtil.isBlank(req.getDatumArea())) {
            return WebResult.okResult(chapteDataService.findDatumList(req.getChapterId(), req.getKNodeId(), req.getDatumType(), pageReq));
        } else {
            return WebResult.okResult(chapteDataService.findDatumList(req.getChapterId(), req.getKNodeId(), req.getDatumArea(), req.getDatumType(), pageReq));
        }
    }

    @UserLoginToken
    @ApiOperation(value = "删除单个文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "datumArea", dataType = "string", value = "资料领域", example = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例 6 复习参考, 不传值是全部需要移除"),
            @ApiImplicitParam(name = "fileId", value = "资料id", dataType = "string", paramType = "form")
    })
    @PostMapping(path = "/removeOne")
    public WebResult removeOne(@RequestBody ChapterDataRemoveReq chapterDataRemoveReq){
        MyAssert.isNull(chapterDataRemoveReq.getFileId(), DefineCode.ERR0010, "资料id不为空");
        MyAssert.isNull(chapterDataRemoveReq.getDatumArea(), DefineCode.ERR0010, "资料领域不为空");
        chapteDataService.removeOne(chapterDataRemoveReq.getFileId(), chapterDataRemoveReq.getDatumArea());
        return WebResult.okResult();
    }

    @UserLoginToken
    @ApiOperation(value = "移除挂载的文件资料信息", notes = "删除一类文件挂载信息或全部挂载信息")
    @PostMapping("/removeChapteDataList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目编号", dataType = "string", required = true, paramType = "form"),
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "form"),
            @ApiImplicitParam(name = "datumType", value = "资料类型", dataType = "string", paramType = "form", example  = "资料类型 1文档　2图册　3视频　4音频　5链接, (多个用‘,’分割), 不传值是全部需要删除挂载")
    })
    public WebResult removeChapteDataList(@RequestBody ChapterDataRemoveReq chapterDataRemoveReq){
        MyAssert.isNull(chapterDataRemoveReq.getCourseId(), DefineCode.ERR0010, "科目编号不为空");
        MyAssert.isNull(chapterDataRemoveReq.getChapterId(), DefineCode.ERR0010, "科目编号不为空");
        chapteDataService.removeChapteDataList(chapterDataRemoveReq.getCourseId(), chapterDataRemoveReq.getChapterId(), chapterDataRemoveReq.getDatumType());
        return WebResult.okResult();
    }
}
