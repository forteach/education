package com.forteach.education.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

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
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String chapterId;

    @Column(name = "chapter_name", columnDefinition = "CHAR(60) COMMENT '章节名称'")
    private String chapterName;

    @Column(name = "chapter_parentId", columnDefinition = "CHAR(32) COMMENT '章节父编号'")
    private String chapterParentId;

    @ApiModelProperty(name = "层级位置", value = "sort", notes = "树型结构所处的顺序默认１")
    @Column(name = "sort", columnDefinition = "INT　DEFAULT 1 COMMENT '当前层所处的顺序'")
    private Integer sort;

    @Column(name = "chapter_level", columnDefinition = "INT COMMENT '章节 树层级'")
    private Integer chapterLevel;
}
