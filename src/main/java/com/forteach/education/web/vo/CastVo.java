package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 3:09
 */
@Data
@ApiModel(value = "角色与集合模型")
public class CastVo {
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", notes = "角色ID", name = "roleId", example = "0")
    private String roleId;

    /**
     * 用户ID集合
     */
    @ApiModelProperty(value = "用户ID集合", notes = "用户ID集合", name = "userIds", example = "[1,2,3,4]")
    private List<String> userIds;
}
