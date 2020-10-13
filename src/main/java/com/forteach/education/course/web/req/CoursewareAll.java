package com.forteach.education.course.web.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "文件图册信息")
public class CoursewareAll implements Serializable {

    /**
     * 文件编号（也可能是图集编号）
     */
    @ApiModelProperty(name = "id", value = "文件编号", dataType = "string")
    public String id;

    /**
     * 文件名称
     */
    @ApiModelProperty(name = "fileName", value = "文件名称", dataType = "string")
    public String fileName;
    /**
     * 图集的话，可以没有URL
     */
    @ApiModelProperty(name = "fileUrl", value = "文件路径URL", dataType = "string", notes = "图集的话，可以没有URL")
    public String fileUrl;

}
