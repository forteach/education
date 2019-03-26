package com.forteach.education.course.web.res;


import com.forteach.education.course.web.req.CoursewareAll;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseAtlitListResp implements Serializable {

    @ApiModelProperty(name = "chapterId", value = "章节id", dataType = "string")
    public String chapterId;

    @ApiModelProperty(name = "fileCount", value = "文件数量", dataType = "int")
    public int fileCount;

    @ApiModelProperty(name = "list", value = "文件对象列表", dataType = "list")
    public List<CoursewareAll> list;

}
