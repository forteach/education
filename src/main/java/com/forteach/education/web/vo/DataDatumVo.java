package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

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
@ApiModel(value = "保存文件需要模型数据")
public class DataDatumVo {

    @NotEmpty(message = "文件名称不为空")
    @ApiModelProperty(value = "文件名称", name = "fileName", dataType = "string")
    private String fileName;

    @NotEmpty(message = "文件路径不为空")
    @ApiModelProperty(value = "文件路径", name = "filePath", dataType = "string")
    private String filePath;
}
