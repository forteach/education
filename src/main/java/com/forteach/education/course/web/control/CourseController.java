package com.forteach.education.course.web.control;

import com.alibaba.fastjson.JSONObject;
import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.web.req.CourseFindAllReq;
import com.forteach.education.course.web.res.CourseListResp;
import com.forteach.education.course.web.res.CourseResp;
import com.forteach.education.course.web.res.CourseSaveResp;
import com.forteach.education.course.web.res.CourseUsersResp;
import com.forteach.education.course.web.vo.RCourse;
import com.forteach.education.databank.domain.ziliao.ViewDatum;
import com.forteach.education.course.service.CourseService;
import com.forteach.education.course.service.CourseShareService;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.req.CourseImagesReq;
import com.forteach.education.course.web.req.CourseSaveReq;
import com.forteach.education.web.vo.DataDatumVo;
import com.forteach.education.common.web.vo.SortVo;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toList;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 16:52
 * @Version: 1.0
 * @Description:　科目课程信息操作
 */
@Slf4j
@RestController
@RequestMapping(path = "/course", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "课程科目操作", tags = {"课程科目操作相关信息"})
public class CourseController {

    @Resource
    private  CourseService courseService;

    @Resource
    private  CourseShareService courseShareService;

    @ApiOperation(value = "保存课程科目信息", notes = "保存科目课程信息")
    @PostMapping("/save")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "course", value = "科目课程对象", dataTypeClass = Course.class, required = true),
            @ApiImplicitParam(name = "oldShareId", value = "编辑修改前条记录的分享编号", dataTypeClass = String.class),
            @ApiImplicitParam(name = "teachers", value = "教师信息列表", dataTypeClass = Teacher.class)
    })
    public WebResult save(@Valid @ApiParam(name = "courseReq", value = "科目课程对象") @RequestBody CourseSaveReq courseReq){
        RCourse rcourse=courseReq.getCourse();
        Course course=new Course();
        UpdateUtil.copyNullProperties(rcourse, course);
        course.setCreateUser(courseReq.getCreateUser());
       List<String> list=courseService.save(course,courseReq.getTeachers());

        //创建输出课程对象
        CourseSaveResp courseSaveResp=CourseSaveResp.builder()
                .courseId(list.get(0))
                .shareId(list.get(1))
                .build();
        return WebResult.okResult(courseSaveResp);

    }

    @ApiOperation(value = "修改科目课程信息", notes = "修改科目信息")
    @PostMapping("/edit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "course", value = "科目课程对象", dataTypeClass = Course.class, required = true),
            @ApiImplicitParam(name = "oldShareId", value = "修改前课程的备课共享编号", dataTypeClass = String.class),
            @ApiImplicitParam(name = "teachers", value = "教师信息列表", dataTypeClass = Teacher.class)
    })
    public WebResult edit(@Valid @ApiParam(name = "courseReq", value = "科目课程对象", required = true) @RequestBody CourseSaveReq courseReq){
        RCourse rcourse=courseReq.getCourse();
        Course course=new Course();
        UpdateUtil.copyNullProperties(rcourse, course);
        String courseId = course.getCourseId();
        Course source = courseService.getById(courseId);
        UpdateUtil.copyNullProperties(source, course);
        course.setCreateTime(source.getCreateTime());
        //创建输出课程对象
        CourseSaveResp courseSaveResp=CourseSaveResp.builder()
                .courseId(course.getCourseId())
                .shareId(courseService.edit(course,courseReq.getOldShareId(),courseReq.getTeachers()))
                .build();
        return WebResult.okResult(courseSaveResp);
    }


    @PostMapping("/getCourse")
    @ApiOperation(value = "获取科目课程信息", notes = "根据科目课程ID查询科目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true, example = "{\"courseId\":\"2c918099676317d0016763e051f50000\"}")
    })
    public WebResult getCourseByCourseId(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据科目ID 查询对应科目信息", type = "string", required = true, example = "{\"courseId\":\"2c918099676317d0016763e051f50000\"}")
                                             @RequestBody String courseId){
       Map result= courseService.getCourseById(String.valueOf(JSONObject.parseObject(courseId).get("courseId")));
        Course course=(Course)result.get("course");
        String shareId=result.get("shareId").toString();
      CourseResp reps= new CourseResp(course.getCourseId(),
                course.getCourseName(),
                course.getCourseNumber(),
                course.getLessonPreparationType(),
                course.getTeachingType(),
                course.getTopPicSrc(),
                course.getShareType(),
                course.getCourseDescribe(),
                shareId);
        return WebResult.okResult(reps);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询分页科目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sortVo", value = "分页参数息", dataTypeClass = CourseFindAllReq.class,example="{\"sortVo\":{\"isValidated\":\"0\",\"page\":0,\"size\":15,\"sort\":1}}")
    })
    @PostMapping("/findAll")
    public WebResult findAll(@Valid @ApiParam(name = "sortVo", value = "分页查科目信息") @RequestBody CourseFindAllReq req){
        SortVo sortVo=req.getSortVo();
        PageRequest page=PageRequest.of(sortVo.getPage(), sortVo.getSize());
        return  WebResult.okResult(courseService.findAll(page).stream()
                .map((item)->{return new CourseListResp(item.getCourseId(),item.getCourseName(),item.getCourseNumber(),item.getLessonPreparationType(),item.getTopPicSrc());})
                .collect(toList()));
    }


    @PostMapping("/findMyCourse")
    @ApiOperation(value = "查询我的课程", notes = "分页查询我的课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户编号", required = true, dataType = "String", type = "String", example = "0001"),
            @ApiImplicitParam(name = "sortVo", value = "分页参数息", dataTypeClass = CourseFindAllReq.class,example="{\"sortVo\":{\"isValidated\":\"0\",\"page\":0,\"size\":15,\"sort\":1}}")
    })
    public WebResult findMyCourse(@Valid @ApiParam(name = "CourseFindAllReq", value = "课程列表请求对象", required = true) @RequestBody CourseFindAllReq req){
        String userId=req.getUserId();
        SortVo sortVo=req.getSortVo();
        PageRequest page=PageRequest.of(sortVo.getPage(), sortVo.getSize());
        return  WebResult.okResult(courseService.findMyCourse(userId,page).stream()
                .map((item)->{return new CourseListResp(item.getCourseId(),item.getCourseName(),item.getCourseNumber(),item.getLessonPreparationType(),item.getTopPicSrc());})
                .collect(toList()));
    }

    @PostMapping("/selectTeachersByShareId")
    @ApiOperation(value = "根据课程备课分享ID查询对应的协作老师信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shareId", value = "科目备课分享ID", dataType = "string", required = true, example = "")
    })
    public WebResult selectTeachersByCourseId(@Valid @ApiParam(name = "shareId", value = "查询对应的协作老师信息", type = "string", required = true) @RequestBody String shareId){
        List<CourseUsersResp> list=courseShareService.findByShareIdUsers(String.valueOf(JSONObject.parseObject(shareId).getString("shareId")))
                .stream().map(item->{
                    CourseUsersResp resp=new CourseUsersResp();
                    UpdateUtil.copyNullProperties(item, resp);
                    return resp;
                }).collect(toList());

        return WebResult.okResult(list);
    }
    //******************************************************************************************************一下内容未修改
    /**
     * 通过文件资源 ID 逻辑删除文件资源信息
     * @param courseId
     * @return
     */
    @ApiOperation(value = "使其无效", notes = "删除科目信息(逻辑删除)")
    @PostMapping("/deleteIsValidById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true)
    })
    public WebResult deleteIsValidById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据资源ID 逻辑删除对应科目信息", type = "string", required = true) @RequestBody String courseId){
        courseService.deleteIsValidById(courseId);
        return WebResult.okResult();
    }

    @ApiOperation(value = "删除科目信息", notes = "删除科目对象 (物理删除)")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "course", value = "科目课程对象", dataTypeClass = Course.class, dataType = "string", required = true),
            @ApiImplicitParam(name = "teachers", value = "教师信息列表", dataTypeClass = Teacher.class)
    })
    public WebResult delete(@Valid @ApiParam(name = "course", value = "科目对象", required = true) @RequestBody Course course){
        courseService.delete(course);
        return WebResult.okResult();
    }

    @ApiOperation(value = "删除文件信息", notes = "根据文件资源ID 删除科目信息")
    @PostMapping("/deleteById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true)
    })
    public WebResult deleteById(@Valid @NotBlank(message = "ID不为空") @ApiParam(name = "courseId", value = "根据科目ID 删除对应科目信息", type = "string", required = true) @RequestBody String courseId){
        courseService.deleteById(String.valueOf(JSONObject.parseObject(courseId).get("courseId")));
        return WebResult.okResult();
    }


    @ApiOperation(value = "保存课程宣传片", notes = "保存课程的宣传片视频信息")
    @PostMapping("/saveViewDatum")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "viewId", value = "视频编号", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "courseId", value = "科目课程ID", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "chapterId", value = "章节编号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "viewName", value = "视频名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "viewType", value = "视频类型", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "viewUrl", value = "视频URL", dataType = "string", paramType = "query")
    })
    public WebResult saveViewDatum(@Valid @NotNull(message = "视频信息不为空") @ApiParam() @RequestBody ViewDatum viewDatum){
        return null;//WebResult.okResult(viewDatumService.save(viewDatum));
    }

    @ApiOperation(value = "根据课程ID查询宣传片信息", notes = "根据课程ID查询宣传片信息")
    @PostMapping("/findViewDatumByCourseId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true)
    })
    public WebResult findViewDatumByCourseId(@Valid @NotBlank(message = "科目课程ID不为空") @ApiParam(name = "courseId", value = "根据课程 ID 查询文件信息", required = true) @RequestBody String courseId){
        return null;//WebResult.okResult(viewDatumService.findViewDatumByCourseId(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }

    /**
     * 保存课程轮播图信息
     * @param courseImagesReq
     * @return
     */
    @PostMapping("/saveCourseImages")
//    @JsonView(View.SummaryExtend.class)
    @ApiOperation(value = "保存课程科目轮播图", notes = "保存科目的轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true),
            @ApiImplicitParam(name = "images", value = "图册信息数组", dataTypeClass = DataDatumVo.class, required = true)
    })
    public WebResult saveCourseImages(@Valid @ApiParam(value = "课程ID和图片", name = "courseImagesReq") @RequestBody CourseImagesReq courseImagesReq){
        courseService.saveCourseImages(courseImagesReq);
        return WebResult.okResult();
    }

    /**
     * 查询轮播图信息
     * @param courseId
     * @return
     */
    @PostMapping("/findImagesByCourseId")
    @ApiOperation(value = "查询课程轮播图", notes = "根据课程科目ID查询对应的轮播图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "科目ID", dataType = "string", required = true, example = "ff808181677d238701677d26fdae0002")
    })
    public WebResult findImagesByCourseId(@Valid @ApiParam(name = "courseId", value = "查询对应的", type = "string", required = true) @RequestBody String courseId){
        return WebResult.okResult(courseService.findImagesByCourseId(String.valueOf(JSONObject.parseObject(courseId).getString("courseId"))));
    }

}
