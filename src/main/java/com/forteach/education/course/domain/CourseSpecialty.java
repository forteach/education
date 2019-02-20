package com.forteach.education.course.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @Description: (课程专业对应)
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:01
 */
@Data
@Entity
@Builder
@Table(name = "courseSpecialty")
@IdClass(CourseSpecialtyPk.class)
public class CourseSpecialty {

    @EmbeddedId
    @ApiModelProperty(value = "课程专业主键", hidden = true)
    private CourseSpecialtyPk courseSpecialtyPk;

    private String courseId;

    private String specialtyId;

}
