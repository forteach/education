package com.forteach.education.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description:　教师
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 14:11
 */
@Data
@Entity
@Table(name = "teacher",indexes = {@Index(columnList = "teacher_id"), @Index(columnList = "specialty_id")})
@EqualsAndHashCode(callSuper = true)
@NotNull
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@org.hibernate.annotations.Table(appliesTo = "teacher", comment = "老师")
public class Teacher extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "teacherId", name = "教师ID", notes = "教师ID", example = "123456")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "teacher_id", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '老师id uuid'")
    private String teacherId;

    @ApiModelProperty(value = "specialty_id", name = "专业号", notes = "专业ID", example = "SD21133")
    @Column(name = "specialty_id", columnDefinition = "VARCHAR(32) COMMENT '专业号'")
    private String specialtyId;

    @NotNull
    @ApiModelProperty(name = "教师名称", value = "teacherName", notes = "教师名称", example = "张三", required = true)
    @Column(name = "teacher_name", columnDefinition = "VARCHAR(40) COMMENT '教师名称'")
    private String teacherName;

    @NotNull
    @ApiModelProperty(name = "教师编号", value = "teacherCode", example = "S1234", required = true)
    @Column(name = "teacher_code", columnDefinition = "VARCHAR(32) COMMENT '教师编号'")
    private String teacherCode;


}
