package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 15:36
 * @Version: 1.0
 * @Description: 分享范围 1、 全部 2、 章节
 */
@Data
@Entity
@Table(name = "course_share", indexes = {@Index(columnList = "share_id"), @Index(columnList = "chapter_id"), @Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(CourseShareFundPrimaryKey.class)
@org.hibernate.annotations.Table(appliesTo = "course_share", comment = "分享范围")
@ApiModel(value = "分享范围")
@AllArgsConstructor
@NoArgsConstructor
public class CourseShare extends Entitys implements Serializable {

    @EmbeddedId
    @ApiModelProperty(value = "分享范围主键", hidden = true)
    private CourseShareFundPrimaryKey courseShareFundPrimaryKey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "分享编号", name = "shareId", dataType = "string")
    private String shareId;

    @ApiModelProperty(value = "科目编号", name = "courseId", dataType = "string")
    private String courseId;

    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string")
    private String chapterId;

    @ApiModelProperty(value = "分享人", name = "shareUser", dataType = "string")
    @Column(name = "share_user", columnDefinition = "VARCHAR(32) COMMENT '分享人'")
    private String shareUser;

    @ApiModelProperty(value = "分享范围", name = "shareArea", dataType = "int")
    @Column(name = "share_area", columnDefinition = "INT COMMENT '分享范围'")
    private Integer shareArea;
}
