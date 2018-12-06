package com.forteach.education.web.req;

import com.forteach.education.web.vo.SortVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-6 17:22
 * @Version: 1.0
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseFileDataReq implements Serializable {

    @ApiModelProperty(name = "isValidated", value = "有无效", dataType = "string")
    private String isValidated;

    @ApiModelProperty(name = "courseId", value = "课程科目ID", dataType = "string")
    private String courseId;

    @ApiModelProperty(name = "chapterId", value = "章节小节ID", dataType = "string")
    private String chapterId;

    @ApiModelProperty(name = "sortVo", value = "分页对象")
    private SortVo sortVo;
}
