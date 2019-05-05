package com.forteach.education.course.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

/**
 * @Description: 课程内容协作访问成员
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 16:42
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "course_share_users", indexes = {@Index(columnList = "share_id", name = "share_id_index"),
        @Index(columnList = "user_id", name = "user_id_index")})
@EqualsAndHashCode(callSuper = true)
@IdClass(CourseShareUsersPk.class)
@ApiModel(value = "课程内容协作访问成员")
public class CourseShareUsers extends Entitys {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
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
