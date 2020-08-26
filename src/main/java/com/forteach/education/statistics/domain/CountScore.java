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
 * @date: 2020/8/19 14:49
 * @version: 1.0
 * @description：学生成绩统计
 */
@Data
@Entity
@Table(name = "count_score", indexes = {
        @Index(columnList = "student_id", name = "student_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "count_score", comment = "学生成绩统计")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "学生成绩统计")
public class CountScore extends Entitys implements Serializable {


    @Id
    @ApiModelProperty(name = "studentId", value = "学生Id", dataType = "string")
    @Column(name = "student_id", columnDefinition = "varchar(32) comment '学生Id'")
    private String studentId;

    @ApiModelProperty(name = "studentName", value = "学生名称", dataType = "string")
    @Column(name = "student_name", columnDefinition = "varchar(32) comment '学生名称'")
    private String studentName;

    @ApiModelProperty(name = "classId", value = "班级Id", dataType = "string")
    @Column(name = "class_id", columnDefinition = "varchar(32) comment '班级Id'")
    private String classId;

    @ApiModelProperty(name = "className", value = "班级名称", dataType = "string")
    @Column(name = "class_name", columnDefinition = "varchar(32) comment '班级名称'")
    private String className;

    @ApiModelProperty(name = "specialtyId", value = "专业号", dataType = "string")
    @Column(name = "specialty_id", columnDefinition = "varchar(32) COMMENT '专业号'")
    private String specialtyId;

    @ApiModelProperty(name = "specialtyName", value = "专业名称", dataType = "string")
    @Column(name = "specialty_name", columnDefinition = "varchar(255) COMMENT '专业名称'")
    private String specialtyName;
    /**
     * 已经考课程数
     */
    @ApiModelProperty(name = "courseNum", value = "已经考课程数", dataType = "int")
    @Column(name = "course_num", columnDefinition = "int(11) DEFAULT 0 comment '已经考的课程数'")
    private Integer courseNum;
    /**
     * 及格数
     */
    @ApiModelProperty(name = "passNum", value = "及格数", dataType = "int")
    @Column(name = "pass_num", columnDefinition = "int(11) DEFAULT 0 comment '及格数'")
    private Integer passNum;
    /**
     * 及格率
     */
    @ApiModelProperty(name = "passRate", value = "及格率", dataType = "string")
    @Column(name = "pass_rate", columnDefinition = "varchar(32) comment '及格率'")
    private String passRate;
    /**
     * 优秀数（80分以上）
     */
    @ApiModelProperty(name = "outstandingNum", value = "优秀数", dataType = "int")
    @Column(name = "outstanding_num", columnDefinition = "int(11) DEFAULT 0 comment '及格率'")
    private Integer outstandingNum;
    /**
     * 优秀率
     */
    @ApiModelProperty(name = "outstandingRate", value = "优秀率", dataType = "string")
    @Column(name = "outstanding_rate", columnDefinition = "varchar(32) comment '优秀率'")
    private String outstandingRate;
}