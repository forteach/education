package com.forteach.education.authority.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-3-4 14:14
 * @version: 1.0
 * @description:
 */
@ApiModel(value = "修改密码需要model数据")
@Data
public class UpdatePassWordReq implements Serializable {

    private String teacherCode;

    @NotBlank(message = "旧密码不为空")
    @ApiModelProperty(name = "oldPassWord", value = "旧密码", required = true, dataType = "string")
    private String oldPassWord;

    @NotBlank(message = "新密码不为空")
    @ApiModelProperty(name = "newPassWord", value = "新密码", required = true, dataType = "string")
    private String newPassWord;
}
