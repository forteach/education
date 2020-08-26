package com.forteach.education.classes.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　教师
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 14:11
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Table(name = "teacher", indexes = {
        @Index(columnList = "teacher_id", name = "teacher_id_index"),
        @Index(columnList = "specialty_id", name = "specialty_id_index")})
@org.hibernate.annotations.Table(appliesTo = "teacher", comment = "老师")
@ApiModel(value = "教师信息")
public class Teacher extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(name = "teacherId", value = "教师ID", notes = "教师ID", example = "ff808181675ea68f01675ea6d86b0000")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "teacher_id", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '老师id uuid'")
    private String teacherId;

    @ApiModelProperty(name = "specialty_id", value = "专业号", notes = "专业ID", required = true, example = "2c9180926746230801674625ad160000")
    @Column(name = "specialty_id", columnDefinition = "VARCHAR(32) COMMENT '专业号'")
    private String specialtyId;

    @ApiModelProperty(value = "教师名称", name = "teacherName", notes = "教师名称", example = "张三", required = true)
    @Column(name = "teacher_name", columnDefinition = "VARCHAR(40) COMMENT '教师名称'")
    private String teacherName;

    @ApiModelProperty(value = "教师编号", name = "teacherCode", example = "S1234", required = true)
    @Column(name = "teacher_code", columnDefinition = "VARCHAR(32) COMMENT '教师编号'")
    private String teacherCode;

    @Column(name = "phone", columnDefinition = "VARCHAR(32) COMMENT '电话号码'")
    private String phone;

    @Column(name = "teacher_office_id", columnDefinition = "varchar(32) comment '教研室id'")
    private String teacherOfficeId;

    @Column(name = "teacher_office_name", columnDefinition = "varchar(32) comment '教研室名称'")
    private String teacherOfficeName;
}
