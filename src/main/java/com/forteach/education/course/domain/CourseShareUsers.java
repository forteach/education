package com.forteach.education.course.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.common.domain.Entitys;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: 课程内容协作访问成员
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 16:42
 */
@Data
@Entity
@Table(name = "course_share_users", indexes = {@Index(columnList = "share_id"), @Index(columnList = "user_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(CourseShareUsersPk.class)
@org.hibernate.annotations.Table(appliesTo = "course_share_users", comment = "课程内容协作访问成员")
@ApiModel(value = "课程内容协作访问成员")
public class CourseShareUsers extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @ApiModelProperty(value = "协作内容成员主键", hidden = true)
    private CourseShareUsersPk shareUsersPk;

    @ApiModelProperty(value = "内容分享ID", name = "shareId", dataType = "string")
    private String shareId;

    @ApiModelProperty(value = "分享成员编号", name = "userId", dataType = "string")
    private String userId;

    @NotBlank(message = "成员名称")
    @ApiModelProperty(name = "courseName", value = "科目名称", dataType = "string", example = "商务英语", required = true)
    @Column(name = "course_name", columnDefinition = "VARCHAR(40) COMMENT '科目名称'")
    @JsonView(View.Summary.class)
    private String courseName;


}
