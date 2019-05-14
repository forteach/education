package com.forteach.education.course.web.req;

import com.forteach.education.web.vo.DataDatumVo;
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
 * @Date: 18-11-29 15:08
 * @Version: 1.0
 * @Description: 课程ID和图片
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "课程图片信息对应数据对象")
public class CourseImagesReq implements Serializable {

    @ApiModelProperty(value = "科目课程ID", name = "courseId", required = true, dataType = "string")
    private String courseId;

    @ApiModelProperty(value = "图片信息", name = "images", required = true)
    private List<DataDatumVo> images;
}
