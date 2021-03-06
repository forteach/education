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
 * @Description: 课堂问题
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:06
 */
@Data
@Entity
@Table(name = "classroom_question", indexes = {@Index(columnList = "teacher_id", name = "teacher_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "classroom_question", comment = "课堂问题")
@ApiModel(value = "课堂问题")
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomQuestion extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "class_question_id", columnDefinition = "VARCHAR(32) COMMENT '问题册编号'")
    @ApiModelProperty(value = "问题册编号", name = "classQuestionId", dataType = "string")
    private String classQuestionId;

    @ApiModelProperty(value = "创建教师", name = "teacherId", dataType = "string")
    @Column(name = "teacher_id", columnDefinition = "VARCHAR(32) COMMENT '创建教师'")
    private String teacherId;

    @ApiModelProperty(value = "科目编号", name = "courseId", dataType = "string")
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    @ApiModelProperty(value = "问题册名称", name = "classQuestionName", dataType = "string")
    @Column(name = "class_question_name", columnDefinition = "VARCHAR(255) COMMENT '问题册名称'")
    private String classQuestionName;

    @ApiModelProperty(value = "问题册类型", name = "classQuestionType", dataType = "int")
    @Column(name = "class_question_type", columnDefinition = "INT(11) COMMENT '问题册类型'")
    private Integer classQuestionType;


}
