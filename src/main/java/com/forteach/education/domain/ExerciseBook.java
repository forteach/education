package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 9:31
 */
@Data
@Entity
@Table(name = "exercise_book", indexes = {@Index(columnList = "teacher_id"),@Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
public class ExerciseBook extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "exe_book_id", columnDefinition = "varchar(32) COMMENT '练习册编号 主键'")
    private String exeBookId;

    @Column(name = "exe_book_type", columnDefinition = "int(32) COMMENT '练习册类型'")
    private Integer exeBookType;

    @Column(name = "teacher_id", columnDefinition = "varchar(32) COMMENT '创建教师'")
    private String teacherId;

    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @Column(name = "exe_book_name", columnDefinition = "varchar(255) COMMENT '练习册名称'")
    private String exeBookName;









}
