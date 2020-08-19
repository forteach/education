package com.forteach.education.course.domain;

import com.forteach.education.common.domain.Entitys;
import com.forteach.education.course.domain.pk.TeacherClassCoursePk;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-26 14:27
 * @version: 1.0
 * @description:　教师对应的班级信息
 */
@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@IdClass(TeacherClassCoursePk.class)
@Table(name = "teacher_class_course", indexes = {
        @Index(columnList = "teacher_id", name = "teacher_id_index"),
        @Index(columnList = "class_id", name = "class_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")})
@org.hibernate.annotations.Table(appliesTo = "teacher_class_course", comment = "老师授课的班级和课程信息")
public class TeacherClassCourse extends Entitys {

    @EmbeddedId
    @ApiModelProperty(value = "课程课堂班级对应主键", hidden = true)
    private TeacherClassCoursePk teacherClassCoursePk;

    /**
     * 教师id
     */
    private String teacherId;

    /**
     * 班级id
     */
    private String classId;

    /**
     * 课程id
     */
    private String courseId;

}
