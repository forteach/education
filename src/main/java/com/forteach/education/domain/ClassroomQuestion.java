package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 课堂问题
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:06
 */
@Data
@Entity
@Table(name = "classroom_question", indexes = {@Index(columnList = "teacher_id"),@Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "classroom_question", comment = "课堂问题")
public class ClassroomQuestion extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "class_question_id", columnDefinition = "varchar(32) COMMENT '问题册编号'")
    private String classQuestionId;

    @Column(name = "teacher_id", columnDefinition = "varchar(32) COMMENT '创建教师'")
    private String teacherId;

    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @Column(name = "class_question_name", columnDefinition = "varchar(255) COMMENT '问题册名称'")
    private String classQuestionName;

    @Column(name = "class_question_type", columnDefinition = "int(11) COMMENT '问题册类型'")
    private Integer classQuestionType;


}
