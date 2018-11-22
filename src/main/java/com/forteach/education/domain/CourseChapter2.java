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
 * @Date: 2018/11/16 19:53
 * @Version: 1.0
 * @Description: 科目章节 2
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@IdClass(CourseChapter2FundPrimaryKey.class)
@Table(name = "course_chapter2", indexes = {@Index(columnList = "chapter_id"), @Index(columnList = "choice_qst_id")})
@org.hibernate.annotations.Table(appliesTo = "course_chapter2", comment = "科目章节 2")
public class CourseChapter2 extends Entitys implements Serializable {

    @EmbeddedId
    @ApiModelProperty(value = "班级分组主键", hidden = true)
    private CourseChapter2FundPrimaryKey courseChapter2FundPrimaryKey;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String chapterId;

    private String choiceQstId;

    @Column(name = "chapter_name", columnDefinition = "CHAR(60) COMMENT '章节名称'")
    private String chapterName;

    @Column(name = "chapter_parent_id", columnDefinition = "CHAR(32) COMMENT '章节父编号'")
    private String chapterParentId;
}
