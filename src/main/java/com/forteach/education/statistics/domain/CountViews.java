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
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/8/25 15:17
 * @Version: v1.0
 * @Modified：访问量统计
 * @Description:
 */
@Data
@Entity
@Table(name = "count_views", indexes = {
        @Index(columnList = "teacher_id", name = "teacher_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "count_views", comment = "访问量统计")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "访问量统计")
public class CountViews extends Entitys implements Serializable {
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
     * 课程访问量
     */
    @ApiModelProperty(name = "courseViewsNum", value = "课程访问量", dataType = "int")
    @Column(name = "coure_views_num", columnDefinition = "int(11) DEFAULT 0 comment '课程访问量'")
    private Integer courseViewsNum;

    /**
     * 资料访问数量
     */
    @ApiModelProperty(name = "dataViewsNum", value = "资料访问量", dataType = "int")
    @Column(name = "data_views_num", columnDefinition = "int(11) DEFAULT 0 comment '资料访问量'")
    private Integer dataViewsNum;

    /**
     * 习题访问量
     */
    @ApiModelProperty(name = "questionViewsNum", value = "习题访问数量", dataType = "int")
    @Column(name = "question_views_num", columnDefinition = "int(11) DEFAULT 0 comment '习题访问数量'")
    private Integer questionViewsNum;
}
