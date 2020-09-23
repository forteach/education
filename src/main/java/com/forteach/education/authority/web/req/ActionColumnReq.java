package com.forteach.education.authority.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-12 15:48
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "系统栏目对像", description = "注册修改栏目对象属性")
public class ActionColumnReq implements Serializable {

    @ApiModelProperty(name = "colId", value = "栏目编号", dataType = "string")
    private String colId;

    @ApiModelProperty(name = "colName", required = true, value = "栏目名称", dataType = "string")
    private String colName;

    @ApiModelProperty(name = "colNameModel", required = true, value = "栏目跳转", dataType = "string")
    private String colNameModel;

    @ApiModelProperty(name = "colParentId", value = "父栏目编号", dataType = "string", notes = "没有父栏目不用传数据")
    private String colParentId;

    @ApiModelProperty(name = "colParentName", value = "父栏目名称", dataType = "string", notes = "没有父栏目不用传数据")
    private String colParentName;

    @ApiModelProperty(name = "colUrl", value = "链接地址", required = true, dataType = "string")
    private String colUrl;

    @ApiModelProperty(name = "colImgUrl", value = "链接图标", required = true, dataType = "string")
    private String colImgUrl;

    @ApiModelProperty(name = "isOrder", value = "栏目顺序", dataType = "int")
    private Integer isOrder;
}
