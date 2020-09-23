package com.forteach.education.authority.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-2-22 14:38
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "用户注册所需数据", description = "注册添加教师信息对象")
public class RegisterUserReq implements Serializable {

    @ApiModelProperty(value = "用户名", name = "userName", required = true, dataType = "string")
    private String userName;

    @ApiModelProperty(value = "密码", name = "passWord", required = true, dataType = "string")
    private String passWord;

    @ApiModelProperty(value = "teacherCode", name = "教师代码", required = true, dataType = "string")
    private String teacherCode;
}
