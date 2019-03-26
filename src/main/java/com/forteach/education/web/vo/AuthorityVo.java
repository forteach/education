package com.forteach.education.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/1 2:33
 */
@Data
@ApiModel(value = "角色相关操作对象")
public class AuthorityVo implements Serializable {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", notes = "用户ID", name = "userId", example = "0")
    private String userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", notes = "删除时需要 角色ID ", name = "roleId", example = "0")
    private String roleId;

    /**
     * 栏目ID
     */
    @ApiModelProperty(value = "栏目ID", notes = "栏目ID", name = "colId", example = "0")
    private String colId;

    /**
     * 需要保存的栏目动作集合
     */
    @ApiModelProperty(value = "需要保存的栏目动作集合", notes = "json 串", name = "json", example = "")
    private String json;
}
