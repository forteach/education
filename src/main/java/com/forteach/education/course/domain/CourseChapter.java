package com.forteach.education.course.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Table(name = "course_chapter", indexes = {@Index(columnList = "chapter_id"), @Index(columnList = "course_id")})
@org.hibernate.annotations.Table(appliesTo = "course_chapter", comment = "科目章节")
@ApiModel(value = "科目章节")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@AllArgsConstructor
public class CourseChapter extends Entitys implements Serializable {

    @NotNull(message = "科目编号不为空")
    @ApiModelProperty(name = "科目编号", value = "courseId", dataType = "string", required = true)
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string", hidden = true)
    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    private String chapterId;

    @NotBlank(message = "章节名称不为空")
    @ApiModelProperty(value = "章节名称", name = "chapter_name", dataType = "string", required = true)
    @Column(name = "chapter_name", columnDefinition = "CHAR(60) COMMENT '章节名称'")
    private String chapterName;

    @ApiModelProperty(value = "章节父编号", name = "chapterParentId", dataType = "string")
    @Column(name = "chapter_parent_id", columnDefinition = "CHAR(32) COMMENT '章节父编号'")
    private String chapterParentId;

    @NotNull(message = "当前层级位置不为空")
    @ApiModelProperty(value = "层级位置", name = "sort", required = true, dataType = "int", notes = "树型结构所处的顺序默认１")
    @Column(name = "sort", columnDefinition = "TINYINT DEFAULT 1 COMMENT '当前层所处的顺序'")
    private Integer sort = 1;

    @ApiModelProperty(name = "chapterType", value = "目录类型", notes = "目录类型：1.章、２.节、3.小节", dataType = "int", required = true)
    @Column(name = "chapter_type", columnDefinition = "TINYINT(1) COMMENT '目录类型：1.章、２.节、3.小节 '")
    private Integer chapterType;

    @Column(name = "chapter_level", columnDefinition = "TINYINT(1) COMMENT '层级　树层级'")
    private Integer chapterLevel;

    @NotNull(message = "是否发布")
    @ApiModelProperty(value = "是否发布", name = "publish", dataType = "string", required = true, notes = "是否发布　Y(是) N(否)", example = "Y")
    @Column(name = "publish", columnDefinition = "CHAR(1) COMMENT '是否发布 Y/N'")
    public String publish;

    public CourseChapter() {
    }

    public CourseChapter(String courseId, String chapterName, String chapterParentId, Integer sort, String publish) {
        this.courseId = courseId;
        this.chapterName = chapterName;
        this.chapterParentId = chapterParentId;
        this.sort = sort;
        this.publish = publish;
    }
}