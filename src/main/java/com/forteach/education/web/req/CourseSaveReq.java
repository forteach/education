package com.forteach.education.web.req;

import com.forteach.education.domain.Course;
import com.forteach.education.domain.Teacher;
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
public class CourseSaveReq implements Serializable {

    @ApiModelProperty(value = "保存课程信息", name = "course")
    private Course course;

    @ApiModelProperty(value = "教师信息", name = "teachers", notes = "如果是协作字段对象是必传字段", required = true)
    private List<Teacher> teachers;
}
