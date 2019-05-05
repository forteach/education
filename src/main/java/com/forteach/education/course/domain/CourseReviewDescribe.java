package com.forteach.education.course.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-29 14:14
 * @version: 1.0
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@org.hibernate.annotations.Table(appliesTo = "course_review_describe", comment = "课程评论详情")
@Table(name = "course_review_describe", indexes =
        {@Index(columnList = "review_id", name = "review_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")})
public class CourseReviewDescribe extends Entitys {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "评论编号", name = "review_id", dataType = "string")
    @Column(name = "review_id", columnDefinition = "VARCHAR(32) COMMENT '评论编号'")
    private String reviewId;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程id'")
    private String courseId;

    @Column(name = "review_describe", columnDefinition = "TEXT(5000) COMMENT '评论内容'")
    private String reviewDescribe;

    @Column(name = "student_id", columnDefinition = "VARCHAR(32) COMMENT '学生id'")
    private String studentId;

    @Column(name = "score", columnDefinition = "INT(11) COMMENT '评论分数'")
    private String scort;

    @Column(name = "teacher_id", columnDefinition = "VARCHAR(32) COMMENT '教师id'")
    private String teacherId;

    @Column(name = "reply", columnDefinition = "TEXT(5000) COMMENT '老师回复内容'")
    private String reply;
}
