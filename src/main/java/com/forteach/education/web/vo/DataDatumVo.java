package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotNull(message = "文件名称不为空")
    @ApiModelProperty(value = "文件名称", name = "fileName", dataType = "string", required = true)
    private String fileName;

    @NotNull(message = "文件路径不为空")
    @ApiModelProperty(value = "文件路径", name = "fileUrl", dataType = "string", required = true)
    private String fileUrl;

    @Length(max = 1, message = "是否挂载最大长度为1位")
    @Pattern(regexp = "^[Y,Z]$", message = "是否挂载文件参数只能是Y,N")
    @ApiModelProperty(name = "mount", value = "是否挂载文件", notes = "是否挂载文件是Y,否 N", example = "Y", dataType = "string")
    private String mount;

    @ApiModelProperty(value = "图片顺序下标", name = "indexNum", dataType = "int", hidden = true)
    private Integer indexNum;
}
