package com.forteach.education.statistics.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 2020/8/19 14:51
 * @version: 1.0
 * @description：学习统计
 */
@Data
@Entity
@Table(name = "count_learn", indexes = {
        @Index(columnList = "student_id", name = "student_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "count_learn", comment = "学习统计")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "学习统计")
public class CountLearn extends Entitys implements Serializable {


    @Id
    @ApiModelProperty(name = "student_id", value = "学生Id", dataType = "string")
    @Column(name = "student_id", columnDefinition = "varchar(32) comment '学生Id'")
    private String studentId;

    @ApiModelProperty(name = "studentName", value = "学生名称", dataType = "string")
    @Column(name = "student_name", columnDefinition = "varchar(32) comment '学生名称'")
    private String studentName;


    @ApiModelProperty(name = "courseId", value = "课程Id", dataType = "string")
    @Column(name = "course_id", columnDefinition = "varchar(32) comment '课程Id'")
    private String courseId;

    @ApiModelProperty(name = "courseName", value = "课程名称", dataType = "string")
    @Column(name = "course_name", columnDefinition = "varchar(32) comment '课程名称'")
    private String courseName;

    @ApiModelProperty(name = "classId", value = "班级Id", dataType = "int")
    @Column(name = "class_id", columnDefinition = "varchar(32) comment '班级Id'")
    private String classId;

    @ApiModelProperty(name = "className", value = "班级名称", dataType = "string")
    @Column(name = "class_name", columnDefinition = "varchar(32) comment '班级名称'")
    private String className;

    @ApiModelProperty(name = "specialtyId", value = "专业id", dataType = "string")
    @Column(name = "specialty_id", columnDefinition = "varchar(32) COMMENT '专业id号'")
    private String specialtyId;

    @ApiModelProperty(name = "specialtyName", value = "专业名称", dataType = "string")
    @Column(name = "specialty_name", columnDefinition = "varchar(255) COMMENT '专业名称'")
    private String specialtyName;
    /**
     * 课程数
     */
    @ApiModelProperty(name = "courseNum", value = "课程数", dataType = "int")
    @Column(name = "course_num", columnDefinition = "int(11) DEFAULT 0 comment '课程数'")
    private Integer courseNum;
    /**
     * 课堂练习数
     */
    @ApiModelProperty(name = "classRoomExerciseNum", value = "课堂练习数", dataType = "int")
    @Column(name = "class_room_exercise_num", columnDefinition = "int(11) DEFAULT 0 comment '作业数'")
    private Integer classRoomExerciseNum;
    /**
     * 作业数
     */
    @ApiModelProperty(name = "homeworkNum", value = "作业数", dataType = "int")
    @Column(name = "homework_num", columnDefinition = "int(11) default 0 comment '作业数'")
    private Integer homeworkNum;
    /**
     * 心得交流数
     */
    @ApiModelProperty(name = "experienceExchangeNum", value = "心得交流数", dataType = "int")
    @Column(name = "experience_exchange_num", columnDefinition = "int(11) default 0 comment '心得交流数'")
    private Integer experienceExchangeNum;
}