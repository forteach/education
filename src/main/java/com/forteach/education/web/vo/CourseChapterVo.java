package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 11:27
 * @Version: 1.0
 * @Description: 查询科目章节VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseChapterVo implements Serializable {

    @NotEmpty(message = "查询有效不为空")
    @ApiModelProperty(value = "isValidated", name = "有效无效", notes = "０有效　1 无效",dataType = "string", example = "0")
    private String isValidated;

    @NotEmpty(message = "科目ID不为空")
    @ApiModelProperty(value = "courseId", name = "科目ID", notes = "科目ID", dataType = "string")
    private String courseId;
}
