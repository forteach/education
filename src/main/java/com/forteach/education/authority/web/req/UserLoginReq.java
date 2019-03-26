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

    @ApiModelProperty(name = "teacherCode", value = "教师代码", dataType = "string", required = true)
    private String teacherCode;

    @ApiModelProperty(name = "passWord", value = "用户密码", dataType = "string", required = true)
    private String passWord;
}
