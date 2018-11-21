package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 15:09
 * @Version: 1.0
 * @Description:　文件上传保存ＶO
 */
@Data
@Builder
@ApiModel(value = "文件上传相关model")
public class FileUploadVo implements Serializable {


    @NotEmpty(message = "文件类型不为空")
    @Size(min = 1, max = 4, message = "文件不正确")
    @ApiModelProperty(name = "文件类型", value = "fileType", notes = "文件类型, 1文档 ２音频 ３视频 4链接", dataType = "int", required = true)
    private Integer fileType;

    @ApiModelProperty(value = "fileName", name = "文件名称",notes = "上传的文件名称", dataType = "string", required = true)
    @NotEmpty(message = "文件名不能为空")
    private String fileName;

    @ApiModelProperty(value = "filePath", name = "文件保存的路径", notes = "在文件服务器保存对应的URL", dataType = "string", required = true)
    @NotEmpty(message = "文件路径不能为空")
    private String filePath;

}
