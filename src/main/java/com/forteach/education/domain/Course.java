package com.forteach.education.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.filter.View;
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
@Table(name = "course", indexes = {@Index(columnList = "course_id"), @Index(columnList = "specialty_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "course", comment = "科目课程")
@ApiModel(value = "科目课程")
public class Course extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    @ApiModelProperty(value = "科目编号ID", name = "courseId", dataType = "string", example = "ff808181673e8df401673e8e49cb0000")
    @JsonView(View.SummaryExtend.class)
    private String courseId;

    @NotNull(message = "专业号不为空")
    @ApiModelProperty(name = "specialtyId", value = "专业号", dataType = "string", example = "2c9180926746230801674625e56f0001", required = true)
    @Column(name = "specialty_id", columnDefinition = "VARCHAR(32) COMMENT '专业号'")
    @JsonView(View.Summary.class)
    private String specialtyId;

    @NotBlank(message = "科目名称")
    @ApiModelProperty(name = "courseName", value = "科目名称", dataType = "string", example = "商务英语", required = true)
    @Column(name = "course_name", columnDefinition = "VARCHAR(40) COMMENT '科目名称'")
    @JsonView(View.Summary.class)
    private String courseName;

    @NotEmpty(message = "编号不为空")
    @ApiModelProperty(name = "courseNumber", value = "科目编号",dataType = "string", example = "S123456", required = true)
    @Column(name = "course_number", columnDefinition = "VARCHAR(32) COMMENT '课程编号'")
    @JsonView(View.Summary.class)
    private String courseNumber;

    @NotNull(message = "分享类型不为空")
    @Range(min = 1, max = 3, message = "分享类型错误")
    @ApiModelProperty(name = "shareType", value = "分享类型", dataType = "int", notes = "分享类型：１.私有 2.协作 ３.公开,默认私有", example = "2", required = true)
    @Column(name = "share_type", columnDefinition = "INT DEFAULT 1 COMMENT '分享类型：１.私有 2.协作 ３.公开'")
    @JsonView(View.Summary.class)
    private Integer shareType = 1;

    //　TODO --> 授课类型是多选
    @NotNull(message = "授课类型不为空")
    @ApiModelProperty(value = "授课类型", name = "teachingType", dataType = "int", notes = "1、录播课程 2、直播课程 3、线下课堂", example = "1,2,3,", required = true)
    @Column(name = "teaching_type", columnDefinition = "VARCHAR(32) COMMENT '1、录播课程 2、直播课程 3、线下课堂'")
    @JsonView(View.Summary.class)
    private String teachingType;

//    @NotEmpty(message = "备课类型不为空")
//    @Size(min = 1, max = 2, message = "备课类型不正确")
//    @ApiModelProperty(value = "备课类型", name = "lessonPreparationType", notes = "1、单人备课２、集体备课", required = true)
//    @Column(name = "lesson_preparation_type", columnDefinition = "INT COMMENT '备课类型　1、单人备课２、集体备课'")
//    private Integer LessonPreparationType;

//    @URL(message = "不是一个URL")
    @NotBlank(message = "封面图片路径")
    @ApiModelProperty(value = "封面图片路径", name = "topPicSrc", notes = "保存的是封面图片路径", required = true, example = "http://wx2.sinaimg.cn/large/006nLajtly1fk65lrevkqj30dw0dwadz.jpg")
    @Column(name = "top_pic_src", columnDefinition = "VARCHAR(255) COMMENT'封面图片路径'")
    @JsonView(View.Summary.class)
    private String topPicSrc;

    @Column(name = "course_describe", columnDefinition = "MEDIUMTEXT COMMENT'课程描述'")
    @ApiModelProperty(name = "courseDescribe", value = "课程描述富文本", notes = "长字段富文本", example = "<p>富文本</p>", required = true)
    @JsonView(View.Summary.class)
    private String courseDescribe;
}
