package com.forteach.education.course.web.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-6-19 15:29
 * @version: 1.0
 * @description:
 */
@Data
public class CourseDataDeleteReq implements Serializable {

    @ApiModelProperty(name = "chapterId", value = "章节ID", dataType = "string", notes = "课程章节不能全部为空")
    private String chapterId;

    @ApiModelProperty(name = "fileIds", value = "需要删除的文件挂载id", dataType = "List", example = "没有参数全部删除挂载资料信息")
    private List<String> fileIds;

    @ApiModelProperty(hidden = true)
    private String updateUser;
}
