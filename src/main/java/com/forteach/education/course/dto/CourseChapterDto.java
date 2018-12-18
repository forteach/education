package com.forteach.education.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
public class CourseChapterDto {

    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string", required = true)
    private String chapterId;

    @ApiModelProperty(value = "章节名称", name = "chapterName", dataType = "string", required = true)
    private String chapterName;

    @ApiModelProperty(value = "章节父ID", name = "chapterParentId", dataType = "string")
    private String chapterParentId;

    @ApiModelProperty(value = "是否发布", name = "publish", dataType = "string", notes = "是否发布　Y(是) N(否)", example = "Y")
    private String publish;

    @ApiModelProperty(value = "层级位置", name = "sort", dataType = "int", notes = "当前层级位置")
    private Integer sort;

    @ApiModelProperty(name = "chapterLevel", value = "层级位置", notes = "当前章节在树状目录处于层级位置")
    private Integer chapterLevel;

    public CourseChapterDto() {
    }

    public CourseChapterDto(String chapterId, String chapterName, String chapterParentId, String publish, Integer sort, Integer chapterLevel) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterParentId = chapterParentId;
        this.publish = publish;
        this.sort = sort;
        this.chapterLevel = chapterLevel;
    }
}
