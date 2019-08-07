package com.forteach.education.authority.web.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2019/8/7 12:07
 * @Version: 1.0
 * @Description:
 */
@Data
public class SysUserEditReq implements Serializable {

    @NotBlank(message = "id不能为空")
    @ApiModelProperty(name = "id", value = "用户id", dataType = "string")
    private String id;

    @ApiModelProperty(name = "registerPhone", value = "注册电话", dataType = "string")
    private String registerPhone;

    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(name = "email", value = "邮箱", dataType = "string")
    private String email;
}