package com.forteach.education.course.domain.pk;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-27 15:32
 * @version: 1.0
 * @description:
 */
@Data
@Embeddable
public class CourseStudyPk implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'", insertable = false, updatable = false)
    private String courseId;

    @Column(name = "student_id", columnDefinition = "VARCHAR(32) COMMENT '学生id'", insertable = false, updatable = false)
    private String studentId;
}