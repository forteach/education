package com.forteach.education.course.web.res;

import com.forteach.education.course.domain.CourseShareUsersPk;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */
@Data
@ApiModel(value = "课程协作教师")
public class CourseUsersResp implements Serializable {

    @ApiModelProperty(value = "协作内容成员主键", hidden = true)
    private CourseShareUsersPk shareUsersPk;

    @ApiModelProperty(value = "内容分享ID", name = "shareId", dataType = "string", example = "40288d5c67df334e0167df3393aa0001")
    private String shareId;

    @ApiModelProperty(value = "接受分享成员编号", name = "userId", dataType = "string")
    private String userId;

    @ApiModelProperty(name = "user_name", value = "接受分享成员名称", dataType = "string", required = true)
    @Column(name = "user_name", columnDefinition = "VARCHAR(40) COMMENT '接受分享成员名称'")
    private String userName;

}
