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
 * @date: 2020/8/19 14:48
 * @version: 1.0
 * @description：课程统计信息
 */
@Data
@Entity
@Table(name = "count_course", indexes = {
        @Index(columnList = "course_id", name = "course_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "count_course", comment = "课程统计信息")
@ApiModel(value = "课程统计信息")
@AllArgsConstructor
@NoArgsConstructor
public class CountCourse extends Entitys implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(name = "courseId", value = "课程Id", dataType = "string")
    @Column(name = "course_id", columnDefinition = "varchar(32) comment '课程Id'")
    private String courseId;

    @ApiModelProperty(name = "courseName", value = "课程名称", dataType = "string")
    @Column(name = "course_name", columnDefinition = "varchar(32) comment '课程名称'")
    private String courseName;

    @ApiModelProperty(name = "teacherNum", value = "教师数量", dataType = "int")
    @Column(name = "teacher_num", columnDefinition = "int(11) DEFAULT 0 comment '教师数'")
    private Integer teacherNum;

    @ApiModelProperty(name = "chapterNum", value = "章节数量", dataType = "int")
    @Column(name = "chapter_num", columnDefinition = "int(11) DEFAULT 0 comment '章节数'")
    private Integer chapterNum;

    @ApiModelProperty(name = "dataNum", value = "资料数量", dataType = "int")
    @Column(name = "data_num", columnDefinition = "int(11) DEFAULT 0 comment '资料数'")
    private Integer dataNum;

    @ApiModelProperty(name = "questionNum", value = "习题数量", dataType = "int")
    @Column(name = "question_num", columnDefinition = "int(11) DEFAULT 0 comment '习题数'")
    private Integer questionNum;
}