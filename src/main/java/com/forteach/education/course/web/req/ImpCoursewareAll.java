package com.forteach.education.course.web.req;

import io.swagger.annotations.ApiModel;
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
 * @Date: 18-12-3 17:09
 * @Version: 1.0
 * @Description: 课程教案、课件
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "课程科目操作 对应数据模型")
public class ImpCoursewareAll implements Serializable {

    /**
     * 章节编号
     */
    @ApiModelProperty(name = "chapterId", value = "章节编号", dataType = "string")
    private String chapterId;

    /**
     * 资料领域：1教案 2课件
     */
    @ApiModelProperty(name = "importantType", dataType = "string", value = "资料领域：1教案 2课件")
    private String importantType;

    /**
     * 文件数量
     */
    @ApiModelProperty(name = "datumType", value = "文件数量", dataType = "string")
    private int fileCount;

    /**
     * 1文件 3、视频
     */
    @ApiModelProperty(name = "datumType", value = "课件类型 1文件 3、视频", dataType = "string")
    private String datumType;

    /**
     * 图集名称
     */
    @ApiModelProperty(name = "photoDatumName", value = "图集名称", dataType = "string")
    private String photoDatumName;

    /**
     * 课件或教案文件列表
     */
    @ApiModelProperty(name = "files", value = "课件或教案文件列表", dataType = "list")
    private List<CoursewareAll> files;

    @ApiModelProperty(hidden = true)
    private String createUser;

    public ImpCoursewareAll(String chapterId, String importantType, int fileCount, String datumType, String photoDatumName, List<CoursewareAll> files) {
        this.chapterId = chapterId;
        this.importantType = importantType;
        this.fileCount = fileCount;
        this.datumType = datumType;
        this.photoDatumName = photoDatumName;
        this.files = files;
    }
}
