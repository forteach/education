package com.forteach.education.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseChapterDto {

    @ApiModelProperty(name = "科目编号", value = "courseId", dataType = "string", required = true)
    private String chapterId;

    @ApiModelProperty(name = "章节名称", value = "chapter_name", dataType = "string", required = true)
    private String chapterName;

    @ApiModelProperty(name = "章节父编号", value = "chapterParentId", dataType = "string")
    private String chapterParentId;

//    @ApiModelProperty(name = "章节　树层级", value = "chapter_level", dataType = "int", notes = "当前章节在所处科目的层级", example = "1")
//    private Integer chapterLevel;
}
