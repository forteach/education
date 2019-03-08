package com.forteach.education.authority.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-2-22 13:47
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "用户登录", description = "教师端登录用户数据")
public class UserLoginReq implements Serializable {

    @NotBlank(message = "用户名不为空")
    @ApiModelProperty(name = "userName", value = "用户名", dataType = "string", required = true)
    private String userName;

    @NotBlank(message = "密码不为空")
    @ApiModelProperty(name = "passWord", value = "用户密码", dataType = "string", required = true)
    private String passWord;
}
