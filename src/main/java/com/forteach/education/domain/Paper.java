package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 试卷
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:18
 */
@Data
@Entity
@Table(name = "paper",  indexes = {@Index(columnList = "teacher_id"),@Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
public class Paper extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'")
    private String paperId;

    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @Column(name = "teacher_id", columnDefinition = "varchar(32) COMMENT '创建教师'")
    private String teacherId;

    @Column(name = "paper_name", columnDefinition = "varchar(100) COMMENT '试卷名称'")
    private String paperName;

    @Column(name = "paper_time", columnDefinition = "int(11) COMMENT '考试时长'")
    private Integer paperTime;

}
