package com.forteach.education.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.classes.service.TeacherService;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 11:05
 * @Version: 1.0
 * @Description: 操作教师数据表
 */
@RestController
@RequestMapping(path = "/teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "教师controller", tags = {"教师操作接口"})
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * 保存教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/save")
//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "保存教师信息", notes = "保存信息不为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specialtyId", value = "专业ID", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "teacherName", value = "教师名称", required = true, dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "teacherCode", value = "教师编号", required = true, dataType = "string", paramType = "from")
    })
    public WebResult save(@Valid @ApiParam(value = "保存教师信息", name = "teacher", required = true) @RequestBody Teacher teacher){
        return WebResult.okResult(teacherService.save(teacher));
    }

//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "教师ID 查询教师信息")
    @PostMapping("/getTeacherById")
    @ApiImplicitParam(name = "teacherId", value = "教师主键ID", required = true, dataType = "string", paramType = "from")
    public WebResult getTeacherById(@Valid @NotBlank(message = "教师Id不为空") @ApiParam(value = "教师ID", name = "teacherId", required = true) @RequestBody String teacherId){
        return WebResult.okResult(teacherService.getTeacherById(String.valueOf(JSONObject.parseObject(teacherId).get("teacherId"))));
    }

    /**
     * 编辑查询教师信息
     * @param teacher
     * @return
     */
//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "修改教师信息")
    @PostMapping("/edit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "specialtyId", value = "专业ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "teacherName", value = "教师名称", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "teacherCode", value = "教师编号", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "isValidated", value = "是否有效", dataType = "string", paramType = "from")
    })
    public WebResult edit(@Valid @ApiParam(value = "修改教师信息", name = "teacher", required = true) @RequestBody Teacher teacher){
        return WebResult.okResult(teacherService.edit(teacher));
    }

    /**
     * 删除教师信息对象
     * @param teacher
     * @return
     */
    @ApiOperation(value = "删除教师信息", notes = "根据教师对象删除教师信息(物理删除)")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", dataType = "string", required = true, paramType = "query"),
            @ApiImplicitParam(name = "specialtyId", value = "专业ID", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "teacherName", value = "教师名称", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "teacherCode", value = "教师编号", dataType = "string", paramType = "from"),
            @ApiImplicitParam(name = "isValidated", value = "是否有效", dataType = "string", paramType = "from")
    })
    public WebResult delete(@Valid @ApiParam(value = "修改教师信息", name = "teacher", required = true) @RequestBody Teacher teacher){
        teacherService.delete(teacher);
        return WebResult.okResult();
    }

    /**
     * 根据ID 删除教师信息
     * @param teacherId
     * @return
     */
    @ApiOperation(value = "删除教师信息", notes = "根据教师　ID 删除教师信息(物理删除)")
    @PostMapping("/deleteById")
    @ApiImplicitParam(name = "teacherId", value = "教师主键ID", required = true, dataType = "string", paramType = "from")
    public WebResult deleteById(@Valid @NotBlank(message = "教师id不为空") @ApiParam(name = "teacherId", value = "教师id", required = true) @RequestBody String teacherId){
        teacherService.deleteById(String.valueOf(JSONObject.parseObject(teacherId).get("teacherId")));
        return WebResult.okResult();
    }

    /**
     * 分页查询用户信息
     * @param sortVo
     * @return
     */
//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页从0开始", required = true, dataType = "int", type = "int", example = "0"),
            @ApiImplicitParam(name = "size", value = "每页数量", required = true, dataType = "int", type = "int", example = "10"),
            @ApiImplicitParam(value = "排序规则", dataType = "string", name = "sorting", example = "cTime", required = true),
            @ApiImplicitParam(value = "有无效", name = "isValidated", dataType = "string",example = "0", required = true),
            @ApiImplicitParam(value = "sort", name = "排序方式", dataType = "int", example = "1")
    })
    public WebResult findAll(@Valid @ApiParam(value = "分页对象", name = "sortVo", required = true) @RequestBody SortVo sortVo){
        return WebResult.okResult(teacherService.findAll(sortVo));
    }

    /**
     * 逻辑删除教师信息使其无效不显示
     * @param teacherId
     */
    @ApiOperation(value = "使其无效", notes = "根据教师　ID 删除教师信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    @ApiImplicitParam(name = "teacherId", value = "教师主键ID", required = true, dataType = "string", paramType = "from")
    public WebResult deleteIsValidById(@Valid @NotBlank(message = "教师id不为空") @ApiParam(name = "teacherId", value = "教师id", required = true) @RequestBody String teacherId){
        teacherService.deleteIsValidById(String.valueOf(JSONObject.parseObject(teacherId).get("teacherId")));
        return WebResult.okResult();
    }

    /**
     * 通过专业ID查询对应的教师列表
     * @param specialtyId
     * @return
     */
//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "查询教师列表", notes = "根据专业ID号查询对应的教师信息")
    @PostMapping("/findTeachersBySpecialtyId")
    @ApiImplicitParam(name = "specialtyId", value = "专业主键ID", required = true, dataType = "string", paramType = "from")
    public WebResult findTeachersBySpecialtyId(@Valid @NotBlank(message = "专业ID信息不为空") @ApiParam(name = "specialtyId", value = "专业ID") @RequestBody String specialtyId){
        return WebResult.okResult(teacherService.findTeachersBySpecialtyId(String.valueOf(JSONObject.parseObject(specialtyId).getString("specialtyId"))));
    }
}
