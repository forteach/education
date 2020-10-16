package com.forteach.education.databank.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 18:58
 * @Version: v1.0
 * @Modified：移动文件或者文件夹
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "移动文件文件夹")
public class MoveOnLineDiskReq implements Serializable {

//    @NotBlank(message = "id不为空")
    @ApiModelProperty(name = "id", value = "主键Id", dataType = "string", required = true)
    private String id;


//    @NotBlank(message = "pId不为空")
    @ApiModelProperty(name = "pId", value = "要移动到的文件夹下的父id", dataType = "string", required = true)
    private String pId;
}