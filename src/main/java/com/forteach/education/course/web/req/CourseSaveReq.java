package com.forteach.education.course.web.req;

import com.forteach.education.classes.web.req.RTeacher;
import com.forteach.education.common.request.BaseReq;
import com.forteach.education.course.web.vo.RCourse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-29 16:21
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "课程添加请求对象")
public class CourseSaveReq implements Serializable {

    @ApiModelProperty(value = "保存课程信息", name = "course")
    private RCourse course;

    @ApiModelProperty(value = "修改前集体备课共享ID", name = "oldShareId")
    private String oldShareId;

    @ApiModelProperty(value = "集体备课教师信息", name = "teachers", notes = "如果是协作字段对象是必传字段")
    private List<RTeacher> teachers;
}
