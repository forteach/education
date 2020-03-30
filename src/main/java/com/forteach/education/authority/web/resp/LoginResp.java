package com.forteach.education.authority.web.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-29 10:01
 * @version: 1.0
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResp implements Serializable {

    @ApiModelProperty(name = "userId", value = "用户id", dataType = "string")
    private String userId;

    @ApiModelProperty(name = "teacherId", value = "教师id", dataType = "string")
    private String teacherId;

    @ApiModelProperty(name = "userName", value = "用户名", dataType = "string")
    private String userName;

    @ApiModelProperty(name = "token", value = "用户请求token", dataType = "string")
    private String token;

    @ApiModelProperty(name = "roleId", value = "角色id", dataType = "string")
    private String roleId;
}
