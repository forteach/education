package com.forteach.education.databank.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: 练习册
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 9:31
 */
@Data
@Entity
@Table(name = "exercise_book", indexes = {@Index(columnList = "teacher_id"), @Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "exercise_book", comment = "练习册")
@ApiModel(value = "练习册")
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseBook extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "exe_book_id", columnDefinition = "varchar(32) COMMENT '练习册编号 主键'")
    @ApiModelProperty(value = "练习册编号", name = "exeBookId", dataType = "string")
    private String exeBookId;

    @ApiModelProperty(value = "练习册类型", name = "exeBookType", dataType = "int", required = true)
    @Column(name = "exe_book_type", columnDefinition = "int COMMENT '练习册类型'")
    private Integer exeBookType;

    @ApiModelProperty(value = "创建教师", name = "teacherId", dataType = "string", required = true)
    @Column(name = "teacher_id", columnDefinition = "varchar(32) COMMENT '创建教师'")
    private String teacherId;

    @ApiModelProperty(value = "科目编号", name = "courseId", dataType = "string", required = true)
    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @ApiModelProperty(value = "练习册名称", name = "exeBookName", dataType = "string", required = true)
    @Column(name = "exe_book_name", columnDefinition = "varchar(255) COMMENT '练习册名称'")
    private String exeBookName;


}
