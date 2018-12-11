package com.forteach.education.dto;

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

    @ApiModelProperty(value = "是否发布", name = "release", dataType = "string", notes = "是否发布　Y(是) N(否)", example = "Y")
    private String release;

    public CourseChapterDto() {
    }

    public CourseChapterDto(String chapterId, String chapterName, String chapterParentId, String release) {
        this.chapterId = chapterId;
        this.chapterName = chapterName;
        this.chapterParentId = chapterParentId;
        this.release = release;
    }
}
