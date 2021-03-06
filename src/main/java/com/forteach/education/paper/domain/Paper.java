package com.forteach.education.paper.domain;

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
 * @Description: 试卷
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:18
 */
@Data
@Entity
@Table(name = "paper", indexes = {@Index(columnList = "teacher_id", name = "teacher_id_index"), @Index(columnList = "course_id", name = "course_id_index")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "paper", comment = "试卷")
@ApiModel(value = "试卷")
@AllArgsConstructor
@NoArgsConstructor
public class Paper extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "paper_id", columnDefinition = "varchar(32) COMMENT '试卷编号'")
    @ApiModelProperty(value = "试卷编号", name = "paperId", required = true)
    private String paperId;

    @ApiModelProperty(value = "courseId", name = "courseId", dataType = "string")
    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @ApiModelProperty(value = "创建教师ID", name = "teacherId", dataType = "string")
    @Column(name = "teacher_id", columnDefinition = "varchar(32) COMMENT '创建教师'")
    private String teacherId;

    @ApiModelProperty(value = "试卷名称", name = "paperName", dataType = "string")
    @Column(name = "paper_name", columnDefinition = "varchar(100) COMMENT '试卷名称'")
    private String paperName;

    @ApiModelProperty(value = "考试时长", name = "paperTime", notes = "考试时长", dataType = "int")
    @Column(name = "paper_time", columnDefinition = "int(11) COMMENT '考试时长'")
    private Integer paperTime;

}
