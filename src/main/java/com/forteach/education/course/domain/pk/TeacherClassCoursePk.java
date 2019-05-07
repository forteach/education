package com.forteach.education.course.domain.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-26 14:37
 * @version: 1.0
 * @description:
 */
@Data
@Embeddable
public class TeacherClassCoursePk implements Serializable {

    @Column(name = "teacher_id", columnDefinition = "VARCHAR(32) COMMENT '老师id'", insertable = false, updatable = false)
    private String teacherId;

    @Column(name = "class_id", columnDefinition = "VARCHAR(32) COMMENT '班级编号'", insertable = false, updatable = false)
    private String classId;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'", insertable = false, updatable = false)
    private String courseId;

    public TeacherClassCoursePk() {
    }

    public TeacherClassCoursePk(String teacherId, String classId, String courseId) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.courseId = courseId;
    }
}
