package com.forteach.education.wechat.web.req;


import com.forteach.education.wechat.web.vo.WxDataVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 19-1-8 15:29
 * @Version: 1.0
 * @Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "绑定学生用户登录微信信息")
public class BindingUserInfoReq extends WxDataVo {

    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;

    @ApiModelProperty(value = "身份证号码", name = "idCardNo")
    private String idCardNo;
}
