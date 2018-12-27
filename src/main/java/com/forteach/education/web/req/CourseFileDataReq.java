package com.forteach.education.web.req;

import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
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
@ApiModel(value = "查询对象")
public class CourseFileDataReq implements Serializable {

    @ApiModelProperty(name = "courseId", value = "课程科目ID", dataType = "string")
    private String courseId;

    @ApiModelProperty(name = "chapterId", value = "章节小节ID", dataType = "string")
    private String chapterId;

    @Length(max = 1, message = "挂载文件查询参数最大一位")
    @Pattern(regexp = "^[Y,N]$",message = "是否挂载参数错误")
    @ApiModelProperty(name = "mount", value = "是否挂载", dataType = "string", notes = "是否挂载Y(是), N(否) ", example = "Y/N")
    private String mount;

    @ApiModelProperty(name = "sortVo", value = "分页对象")
    private SortVo sortVo;
}
