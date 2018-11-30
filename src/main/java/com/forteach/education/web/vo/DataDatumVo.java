package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-28 15:24
 * @Version: 1.0
 * @Description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "保存文件需要模型数据", description = "需要的文件数据信息(必须)")
public class DataDatumVo implements Serializable {

    @ApiModelProperty(value = "文件资料ID", name = "fileId", notes = "文件对应的资料ID编号", dataType = "string", hidden = true)
    private String fileId;

    @NotEmpty(message = "文件名称不为空")
    @ApiModelProperty(value = "文件名称", name = "fileName", dataType = "string", required = true)
    private String fileName;

    @NotEmpty(message = "文件路径不为空")
    @ApiModelProperty(value = "文件路径", name = "filePath", dataType = "string", required = true)
    private String filePath;

    @ApiModelProperty(value = "图片顺序下标", name = "indexNum", dataType = "int", hidden = true)
    private Integer indexNum;
}
