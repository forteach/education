package com.forteach.education.databank.web.req;

import com.forteach.education.common.web.vo.SortVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-29 16:21
 * @Version: 1.0
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "课程所有列表")
public class ChapteDataListReq implements Serializable {

    @ApiModelProperty(name = "courseId", value = "科目编号", dataType = "string", required = true)
    private String courseId;

    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string")
    private String chapterId;

    @ApiModelProperty(name = "datumArea", dataType = "string", value = "资料领域", example = "1", notes = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例,6", required = true)
    private String datumArea;


    @ApiModelProperty(name = "datumType", dataType = "string", value = "资料类型", example = "1", notes = "资料类型 1文档　2图册　3视频　4音频　5链接", required = true)
    private String datumType;

    @ApiModelProperty(name = "kNodeId", value = "知识点标签", dataType = "string", notes = "知识点 ‘,’ 进行分割")
    private String kNodeId;

    @ApiModelProperty(value = "分页排序字段", name = "sortVo")
    private SortVo sortVo = new SortVo();

}
