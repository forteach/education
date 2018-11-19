package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 14:09
 * @Version: 1.0
 * @Description: 科目章节
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@IdClass(CourseChapterFundPrimarykey.class)
@Table(name = "course_chapter", indexes = {@Index(columnList = "chapter_id"), @Index(columnList = "course_id")})
@org.hibernate.annotations.Table(appliesTo = "course_chapter", comment = "科目章节")
public class CourseChapter extends Entitys implements Serializable {

    @EmbeddedId
    private CourseChapterFundPrimarykey courseChapterFundPrimaryKey;

    private String courseId;

    @Id
    private String chapterId;

    @Column(name = "chapter_name", columnDefinition = "CHAR(60) COMMENT '章节名称'")
    private String chapterName;

    @Column(name = "chapter_parentId", columnDefinition = "CHAR(32) COMMENT '章节父编号'")
    private String chapterParentId;
}