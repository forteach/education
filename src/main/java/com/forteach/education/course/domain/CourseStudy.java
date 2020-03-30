package com.forteach.education.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forteach.education.common.domain.Entitys;
import com.forteach.education.course.domain.pk.CourseStudyPk;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlInlineBinaryData;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-27 14:37
 * @version: 1.0
 * @description:
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course_study", indexes = {
        @Index(columnList = "course_id", name = "course_id_index"),
        @Index(columnList = "student_id", name = "student_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "course_study", comment = "科目课程学习情况")
@DynamicInsert
@DynamicUpdate
@IdClass(CourseStudyPk.class)
public class CourseStudy extends Entitys implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private CourseStudyPk courseStudyPk;

    private String courseId;

    private String studentId;

    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '最近学习到的章节位置'")
    private String chapterId;

    @Column(name = "study_status", columnDefinition = "INT(2) DEFAULT 0 COMMENT '学习状态 0 未学习　1 在学习　2 已完结'")
    private Integer studyStatus;

    @Column(name = "semester_grade", columnDefinition = "VARCHAR(32) COMMENT '学期成绩/平时成绩'")
    private String semesterGrade;

    @Column(name = "exam_grade", columnDefinition = "VARCHAR(32) COMMENT '考试成绩'")
    private String examGrade;

    @Column(name = "examResults", columnDefinition = "INT(2) DEFAULT 1 COMMENT '考试结果状态 0 已经通过 1 未通过'")
    private Integer examResults;

    @Column(name = "make_up_examination", columnDefinition = "INT(2) COMMENT '是否需要补考 0 需要补考 1 不需要补考'")
    private Integer makeUpExamination;
}
