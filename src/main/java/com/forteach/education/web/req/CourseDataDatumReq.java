package com.forteach.education.web.req;

import com.forteach.education.course.web.vo.RCourseData;
import com.forteach.education.web.vo.DataDatumVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-28 17:23
 * @Version: 1.0
 * @Description: 保存课程对象
 */
@Data
@ApiModel(value = "课程章节保存文件")
public class CourseDataDatumReq implements Serializable {

    @ApiModelProperty(name = "courseId", value = "课程id", dataType = "string", notes = "课程章节不能全部为空")
    private String courseId;

    @ApiModelProperty(name = "chapterId", value = "章节ID", dataType = "string", notes = "课程章节不能全部为空")
    private String chapterId;

    @ApiModelProperty(value = "files", name = "文件列表信息", required = true)
    private List<RCourseData> files;
}
