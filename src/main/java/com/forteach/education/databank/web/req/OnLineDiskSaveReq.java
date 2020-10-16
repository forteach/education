package com.forteach.education.databank.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 14:14
 * @Version: v1.0
 * @Modified：保存文件信息到数据库
 * @Description:
 */
@Data
@ApiModel(value = "保存上传文件")
public class OnLineDiskSaveReq implements Serializable {
    @ApiModelProperty(name = "id", value = "主键Id", dataType = "int")
    private Long id;
    @ApiModelProperty(name = "pId", value = "父Id", dataType = "int")
    private Long pId;
    @ApiModelProperty(name = "folder", value = "文件夹", dataType = "string")
    private String folder;
    @ApiModelProperty(name = "fileName", value = "文件名", dataType = "string")
    private String fileName;
    @ApiModelProperty(name = "fileUrl", value = "文件url", dataType = "string")
    private String fileUrl;
    @NotBlank(message = "用户Id不为空")
    @ApiModelProperty(name = "userId", value = "所属的用户Id", dataType = "string", required = true)
    private String userId;
    @NotBlank(message = "用户名称不为空")
    @ApiModelProperty(name = "userName", value = "所属的用户名称", dataType = "string", required = true)
    private String userName;
}