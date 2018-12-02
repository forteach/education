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
 * @Date: 2018/11/17 13:44
 * @Version: 1.0
 * @Description: 视频资料库
 */
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "view_datum", indexes = {@Index(columnList = "view_id"), @Index(columnList = "chapter_id")})
@org.hibernate.annotations.Table(appliesTo = "view_datum", comment = "视频资料库")
@ApiModel(value = "视频资料信息")
@AllArgsConstructor
@NoArgsConstructor
public class ViewDatum extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "viewId", value = "视频编号", notes = "视频资源ID", dataType = "string", example = "123456")
    @Column(name = "view_id", columnDefinition = "VARCHAR(32) COMMENT '视频编号'")
    @JsonView(View.SummaryExtend.class)
    private String viewId;

    @JsonView(View.Summary.class)
    @NotNull(message = "科目课程不为空")
    @ApiModelProperty(value = "科目课程ID", notes = "科目课程ID", required = true)
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目课程ID'")
    private String courseId;

    @JsonView(View.Summary.class)
    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    @ApiModelProperty(name = "chapterId", value = "章节编号", notes = "章节编号ID", dataType = "string", example = "123456")
    private String chapterId;

    @JsonView(View.Summary.class)
    @NotNull(message = "视频名称不为空")
    @ApiModelProperty(name = "viewName", value = "视频名称", notes = "视频名称", dataType = "string", required = true, example = "学习视频001")
    @Column(name = "view_name", columnDefinition = "VARCHAR(32) COMMENT '视频名称'")
    private String viewName;

    @JsonView(View.Summary.class)
    @ApiModelProperty(name = "viewType", value = "视频类型", notes = "视频类型", dataType = "string")
    @Column(name = "view_type", columnDefinition = "VARCHAR(10) COMMENT '视频类型'")
    private String viewType;

    @URL(message = "不是一个URL")
    @JsonView(View.Summary.class)
    @NotNull(message = "视频链接地址不为空")
    @ApiModelProperty(name = "viewUrl", value = "视频URL", notes = "视频的链接地址", dataType = "string", required = true, example = "http://www.www./dsdd.mp4")
    @Column(name = "view_url", columnDefinition = "VARCHAR(255) COMMENT '视频URL'")
    private String viewUrl;
}
