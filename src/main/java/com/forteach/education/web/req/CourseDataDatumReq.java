package com.forteach.education.web.req;

import com.forteach.education.web.vo.DataDatumVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-28 17:23
 * @Version: 1.0
 * @Description: 保存课程对象
 */
@Builder
@Data
@ApiModel(value = "课程章节保存文件")
public class CourseDataDatumReq {

    @ApiModelProperty(name = "courseId", value = "课程id")
    private String courseId;

    @ApiModelProperty(name = "chapterId", value = "chapterId")
    private String chapterId;

    @ApiModelProperty(value = "files", name = "文件列表信息")
    private List<DataDatumVo> files;
}
