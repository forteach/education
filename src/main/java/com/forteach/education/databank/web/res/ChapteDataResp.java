package com.forteach.education.databank.web.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


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
public class ChapteDataResp {


 @NotNull(message = "科目编号不为空")
 @ApiModelProperty(name = "courseId", value = "科目编号", dataType = "string", required = true)
 private String courseId;

 @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string")
 private String chapterId;

 @ApiModelProperty(name = "datumArea", dataType = "string", value = "资料领域", example = "1", notes = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例", required = true)
 private String datumArea;

 @NotNull(message = "资料类型不为空")
 @ApiModelProperty(name = "datumType", dataType = "string", value = "资料类型", example = "1", notes = "资料类型 1文档　2图册　3视频　4音频　5链接", required = true)
 private String datumType;

// @ApiModelProperty(name = "kNodeId", value = "知识点标签", dataType = "string", notes = "知识点 ‘,’ 进行分割")
// private String kNodeId;

 @ApiModelProperty(name = "fileName", value = "资料名称", dataType = "string", notes = "资料名称")
 private String fileName;

 @ApiModelProperty(name = "src", value = "资料路径", dataType = "string", notes = "资料路径")
 private String src;
}
