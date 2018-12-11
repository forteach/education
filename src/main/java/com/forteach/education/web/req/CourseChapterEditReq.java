package com.forteach.education.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-11 16:58
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "修改课程章节的对象")
public class CourseChapterEditReq implements Serializable {

    @ApiModelProperty(name = "科目编号", value = "courseId", dataType = "string")
    private String courseId;

    @NotNull(message = "章节编号不为空")
    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string", required = true)
    private String chapterId;

    @ApiModelProperty(value = "章节名称", name = "chapter_name", dataType = "string")
    private String chapterName;

    @ApiModelProperty(value = "章节父编号", name = "chapterParentId", dataType = "string")
    private String chapterParentId;

    @ApiModelProperty(value = "层级位置", name = "sort", required = true, dataType = "int", notes = "树型结构所处的顺序默认１")
    private Integer sort = 1;

    @ApiModelProperty(name = "chapterType", value = "目录类型", notes = "目录类型：1.章、２.节、3.小节", dataType = "int")
    private Integer chapterType;

    @ApiModelProperty(value = "是否发布", name = "publish", dataType = "string", notes = "是否发布　Y(是) N(否)", example = "Y")
    private String publish;
}
