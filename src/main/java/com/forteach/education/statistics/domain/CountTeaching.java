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
 * @date: 2020/8/19 14:52
 * @version: 1.0
 * @description：教学统计
 */
@Data
@Entity
@Table(name = "count_teaching", indexes = {
        @Index(columnList = "teacher_id", name = "teacher_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "count_teaching", comment = "教学统计")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "教学统计")
public class CountTeaching extends Entitys implements Serializable {

    @Id
    @ApiModelProperty(name = "courseId", value = "课程Id", dataType = "string")
    @Column(name = "course_id", columnDefinition = "varchar(32) comment '课程Id'")
    private String courseId;

    @ApiModelProperty(name = "courseName", value = "课程名称", dataType = "string")
    @Column(name = "course_name", columnDefinition = "varchar(32) comment '课程名称'")
    private String courseName;

    @ApiModelProperty(name = "teaherId", value = "教师Id", dataType = "string")
    @Column(name = "teacher_id", columnDefinition = "varchar(32) comment '教师Id'")
    private String teacherId;

    @ApiModelProperty(name = "teacherName", value = "教师名称", dataType = "string")
    @Column(name = "teacher_name", columnDefinition = "varchar(32) comment '教师名称'")
    private String teacherName;
    /**
     * 教研室id
     */
    @ApiModelProperty(name = "teachingOfficeId", value = "教研室Id", dataType = "string")
    @Column(name = "teachar_office_id", columnDefinition = "varchar(32) comment '教研室Id'")
    private String teachingOfficeId;
    /**
     * 教研室名称
     */
    @ApiModelProperty(name = "teachingOfficeName", value = "教研室名称", dataType = "string")
    @Column(name = "teaching_office_name", columnDefinition = "varchar(32) comment '教研室名称'")
    private String teachingOfficeName;
    /**
     * 课程数
     */
    @ApiModelProperty(name = "courseNum", value = "课程数", dataType = "int")
    @Column(name = "course_num", columnDefinition = "int(11) DEFAULT 0 comment '课程数'")
    private Integer courseNum;
    /**
     * 章节数
     */
    @ApiModelProperty(name = "chapterNum", value = "章节数", dataType = "int")
    @Column(name = "chapter_num", columnDefinition = "int(11) DEFAULT 0 comment '章节数'")
    private Integer chapterNum;
    /**
     * 资料数
     */
    @ApiModelProperty(name = "dataNum", value = "资料数", dataType = "int")
    @Column(name = "data_num", columnDefinition = "int(11) DEFAULT 0 comment '资料数'")
    private Integer dataNum;
    /**
     * 题目数
     */
    @ApiModelProperty(name = "questionMum", value = "题目数", dataType = "int")
    @Column(name = "question_num", columnDefinition = "int(11) DEFAULT 0 comment '习题数'")
    private Integer questionNum;
    /**
     * 互动数
     */
    @ApiModelProperty(name = "interactionsNum", value = "互动数", dataType = "string")
    @Column(name = "interactions_num", columnDefinition = "int(11) default 0 comment '互助数'")
    private Integer interactionsNum;
}