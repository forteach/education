package com.forteach.education.databank.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.print.attribute.standard.MediaSize;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 15:14
 * @Version: v1.0
 * @Modified：查询在线网盘文件列表
 * @Description:
 */
@ApiModel(value = "查询在线文件列表")
@Data
public class OnLineDiskListReq implements Serializable {
    @NotBlank(message = "用户Id不为空")
    @ApiModelProperty(name = "userId", value = "用户Id", dataType = "string", required = true)
    private String userId;
    @ApiModelProperty(name = "pId", value = "父Id", dataType = "int", required = true)
    private long pId;
}