package com.forteach.education.classes.web.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:　教师
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 14:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "教师信息")
public class RTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "teacherId", value = "教师ID", notes = "教师ID", example = "ff808181675ea68f01675ea6d86b0000")
    private String teacherId;

    @NotNull(message = "教师名称不能为空")
    @ApiModelProperty(value = "教师名称", name = "teacherName", notes = "教师名称", example = "张三", required = true)
    private String teacherName;



}
