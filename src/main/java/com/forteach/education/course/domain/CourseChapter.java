package com.forteach.education.course.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@Builder
@EqualsAndHashCode(callSuper = true)
@Table(name = "course_chapter", indexes = {@Index(columnList = "chapter_id", name = "chapter_id_index"), @Index(columnList = "course_id", name = "course_id_index")})
@org.hibernate.annotations.Table(appliesTo = "course_chapter", comment = "科目章节")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@AllArgsConstructor
@NoArgsConstructor
public class CourseChapter extends Entitys implements Serializable {

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    private String chapterId;

    @Column(name = "chapter_name", columnDefinition = "CHAR(60) COMMENT '章节名称'")
    private String chapterName;

    @Column(name = "chapter_parent_id", columnDefinition = "CHAR(32) COMMENT '章节父编号'")
    private String chapterParentId;

    @Column(name = "sort", columnDefinition = "CHAR(3) COMMENT '当前层所处的顺序'")
    private String sort;

    @Column(name = "chapter_type", columnDefinition = "CHAR(3) COMMENT '目录类型：1.章、２.节、3.小节 '")
    private String chapterType;

    @Column(name = "chapter_level", columnDefinition = "CHAR(32) COMMENT '层级　树层级'")
    private String chapterLevel;

    @Column(name = "publish", columnDefinition = "CHAR(1) COMMENT '是否发布 Y/N'")
    public String publish;

}
