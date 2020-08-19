package com.forteach.education.databank.web.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-3 17:09
 * @Version: 1.0
 * @Description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatumResp implements Serializable {

    /**
     * 文件编号
     */
    @ApiModelProperty(name = "fileId", value = "文件编号", dataType = "string")
    public String fileId;
    /**
     * 文件领域（预习,复习...）多领域逗号分隔
     */
    @ApiModelProperty(name = "datumArea", value = "文件领域（预习,复习...）多领域逗号分隔", dataType = "string")
    public String datumArea;
    /**
     * 章节编号
     */
    @ApiModelProperty(name = "chapterId", value = "章节编号", dataType = "string")
    public String chapterId;
    /**
     * 文件名称
     */
    @ApiModelProperty(name = "fileName", value = "文件名称", dataType = "string")
    public String fileName;
    /**
     * 文件扩展名
     */
    @ApiModelProperty(name = "fileType", value = "文件扩展名", dataType = "string")
    public String fileType;
    /**
     * 文件URl
     */
    @ApiModelProperty(name = "fileUrl", value = "文件URl", dataType = "string")
    public String fileUrl;
    /**
     * 文件类型（音频、视频....）
     */
    @ApiModelProperty(name = "datumType", value = "文件类型（音频、视频....）", dataType = "string")
    private String datumType;
    /**
     * 教师共享
     */
    @ApiModelProperty(name = "teachShare", value = "教师共享", dataType = "string")
    private String teachShare;
    /**
     * 教师共享
     */
    @ApiModelProperty(name = "stuShare", value = "教师共享", dataType = "string")
    private String stuShare;

}
