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
 * @date: 2020/8/19 14:50
 * @version: 1.0
 * @description：院系统计
 */
@Data
@Entity
@Table(name = "count_faculty", indexes = {
        @Index(columnList = "teacher_id", name = "teacher_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "count_faculty", comment = "院系统计信息")
@ApiModel(value = "院系统计信息")
@AllArgsConstructor
@NoArgsConstructor
public class CountFaculty extends Entitys implements Serializable {
    @Id
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
     * 课程访问量
     */
    @ApiModelProperty(name = "courseViews", value = "课程访问量", dataType = "int")
    @Column(name = "course_views", columnDefinition = "int(11) DEFAULT 0 comment '课程访问量'")
    private Integer courseViews;
    /**
     * 资料访问量
     */
    @ApiModelProperty(name = "dataNum", value = "资料数量", dataType = "int")
    @Column(name = "data_num", columnDefinition = "int(11) DEFAULT 0 comment '资料数'")
    private Integer dataNum;
    /**
     * 题目访问量
     */
    @ApiModelProperty(name = "questionNum", value = "题目数量", dataType = "int")
    @Column(name = "question_num", columnDefinition = "int(11) DEFAULT 0 comment '习题数'")
    private Integer questionNum;
}