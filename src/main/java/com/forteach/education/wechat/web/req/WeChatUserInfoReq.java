package com.forteach.education.wechat.web.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-22 14:58
 * @version: 1.0
 * @description:
 */
@Data
@ApiModel(value = "微信小程序用户信息", description = "微信小程序用户信息")
public class WeChatUserInfoReq implements Serializable {

    @ApiModelProperty(name = "nickName", value = "用户昵称", dataType = "string")
    private String nickName;

    @ApiModelProperty(name = "avatarUrl", value = "用户头像图片的 URL", dataType = "string")
    private String avatarUrl;

    @ApiModelProperty(name = "gender", value = "性别 0：未知、1：男、2：女", dataType = "string")
    private String gender;

    @ApiModelProperty(name = "province", value = "用户所在省份", dataType = "string")
    private String province;

    @ApiModelProperty(name = "city", value = "用户所在城市", dataType = "string")
    private String city;

    @ApiModelProperty(name = "country", value = "用户所在国家", dataType = "string")
    private String country;

}
