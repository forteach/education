package com.forteach.education.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:28
 * @Version: 1.0
 * @Description: 文档资料库
 */
@Builder
@Data
@Entity
@Table(name = "file_datum", indexes = {@Index(columnList = "file_id"), @Index(columnList = "chapter_id")})
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "文件数据信息", value = "文件数据")
@org.hibernate.annotations.Table(appliesTo = "file_datum", comment = "文档资料库")
@AllArgsConstructor
@NoArgsConstructor
public class FileDatum extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "fileId", value = "文件编号")
    @Column(name = "file_id", columnDefinition = "VARCHAR(32) COMMENT '文件编号'")
    @JsonView(View.SummaryExtend.class)
    private String fileId;

    @JsonView(View.Summary.class)
    @NotNull(message = "科目编号不为空")
    @ApiModelProperty(value = "科目编号", name = "courseId", required = true)
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    @JsonView(View.SummaryExtend.class)
    @ApiModelProperty(value = "chapterId", name = "章节编号")
    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    private String chapterId;

    @JsonView(View.Summary.class)
    @NotNull(message = "文件名称不为空")
    @ApiModelProperty(value = "文件名称", required = true, name = "fileName")
    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    private String fileName;

    @JsonView(View.SummaryExtend.class)
    @ApiModelProperty(value = "文件类型", required = true, name = "fileType")
    @Column(name = "file_type", columnDefinition = "VARCHAR(10) COMMENT '文件类型'")
    private String fileType;

    @URL(message = "不是一个URL")
    @JsonView(View.Summary.class)
    @NotNull(message = "文件URL 不为空")
    @ApiModelProperty(value = "文件URL", required = true, name = "fileUrl")
    @Column(name = "file_url", columnDefinition = "VARCHAR(255) COMMENT '文件URL'")
    private String fileUrl;
}
