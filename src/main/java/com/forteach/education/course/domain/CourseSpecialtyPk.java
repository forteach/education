package com.forteach.education.course.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description: 协作人员访问
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:18
 */
@Data
@Embeddable
public class CourseSpecialtyPk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '课程ID'", insertable = false, updatable = false)
    private String courseId;

    @Column(name = "specialty_id", columnDefinition = "varchar(32) COMMENT '专业编号'", insertable = false, updatable = false)
    private String specialtyId;

    public CourseSpecialtyPk() {

    }

    public CourseSpecialtyPk(String courseId, String specialtyId) {
        this.courseId = courseId;
        this.specialtyId = specialtyId;
    }
}
