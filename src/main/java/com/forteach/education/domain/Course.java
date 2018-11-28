package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 科目
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 16:42
 */
@Data
@Entity
@Table(name = "course", indexes = {@Index(columnList = "specialty_id"), @Index(columnList = "specialty_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "course", comment = "科目课程")
@ApiModel(value = "科目课程")
public class Course extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    @ApiModelProperty(value = "科目编号ID", name = "courseId", dataType = "string")
    private String courseId;

    @NotNull(message = "专业号不为空")
    @ApiModelProperty(name = "specialtyId", value = "专业号", dataType = "string")
    @Column(name = "specialty_id", columnDefinition = "VARCHAR(32) COMMENT '专业号'")
    private String specialtyId;

    @NotBlank(message = "科目名称")
    @ApiModelProperty(name = "courseName", value = "科目名称", dataType = "string")
    @Column(name = "course_name", columnDefinition = "VARCHAR(40) COMMENT '科目名称'")
    private String courseName;

    @NotEmpty(message = "编号不为空")
    @ApiModelProperty(name = "courseNumber", value = "科目编号",dataType = "string", required = true)
    @Column(name = "course_number", columnDefinition = "VARCHAR(32) COMMENT '课程编号'")
    private String courseNumber;

    @Range(min = 1, max = 3, message = "分享类型错误")
    @ApiModelProperty(name = "share_type", value = "分享类型", dataType = "int", notes = "分享类型：１.私有 2.协作 ３.公开,默认私有")
    @Column(name = "share_type", columnDefinition = "INT DEFAULT 1 COMMENT '分享类型：１.私有 2.协作 ３.公开'")
    private Integer shareType = 1;

    @NotEmpty(message = "授课类型不为空")
    @ApiModelProperty(value = "授课类型", name = "teachingType", notes = "1、录播课程 2、直播课程 3、线下课堂")
    @Column(name = "teaching_type", columnDefinition = "INT COMMENT '1、录播课程 2、直播课程 3、线下课堂'")
    private Integer teachingType;

    @NotEmpty(message = "备课类型不为空")
    @ApiModelProperty(value = "备课类型", name = "lessonPreparationType", notes = "1、单人备课２、集体备课")
    @Column(name = "lesson_preparation_type", columnDefinition = "INT COMMENT '备课类型　1、单人备课２、集体备课'")
    private Integer LessonPreparationType;

    @NotEmpty(message = "封面图片路径")
    @ApiModelProperty(value = "封面图片路径", name = "topPicSrc", notes = "保存的是封面图片路径")
    @Column(name = "top_pic_src", columnDefinition = "VARCHAR(255) COMMENT'封面图片路径'")
    private String topPicSrc;

    @Column(name = "course_describe", columnDefinition = "MEDIUMTEXT COMMENT'课程描述'")
    @ApiModelProperty(name = "courseDescribe", value = "课程描述富文本", notes = "长字段富文本")
    private String courseDescribe;
}
