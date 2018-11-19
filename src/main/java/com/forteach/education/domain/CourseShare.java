package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class CourseShare extends Entitys implements Serializable {
    @EmbeddedId
    private CourseShareFundPrimaryKey courseShareFundPrimaryKey;
    @Id
    private String shareId;

    private String courseId;

    private String chapterId;

    @Column(name = "share_user", columnDefinition = "VARCHAR(32) COMMENT '分享人'")
    private String shareUser;

    @Column(name = "share_area", columnDefinition = "INT COMMENT '分享范围'")
    private Integer shareArea;
}
