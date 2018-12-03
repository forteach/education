package com.forteach.education.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/16 20:10
 * @Version: 1.0
 * @Description: 章节资料
 */
@Data
@Entity
@Builder
@Table(name = "chapte_data", indexes = {
        @Index(columnList = "data_id"),
        @Index(columnList = "chapter_id")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "chapte_data", comment = "章节资料")
@ApiModel(value = "章节资料库")
@AllArgsConstructor
@NoArgsConstructor
public class ChapteData extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "资料编号", name = "dataId", dataType = "string")
    @Column(name = "data_id", columnDefinition = "VARCHAR(32) COMMENT '资料编号'")
    @JsonView(View.SummaryExtend.class)
    private String dataId;

    @NotNull(message = "科目编号不为空")
    @ApiModelProperty(name = "courseId", value = "科目编号", dataType = "string", required = true)
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程科目ID'")
    @JsonView(View.Summary.class)
    private String courseId;

    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string")
    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    @JsonView(View.Summary.class)
    private String chapterId;

    @ApiModelProperty(name = "sortImgId", value = "图册编号", dataType = "string", hidden = true)
    @Column(name = "sort_img_id", columnDefinition = "VARCHAR(32) COMMENT '图册编号'")
    @JsonView(View.Summary.class)
    private String sortImgId;

    @ApiModelProperty(name = "fileId", value = "文件编号", dataType = "string", hidden = true)
    @Column(name = "file_id", columnDefinition = "VARCHAR(32) COMMENT '文件编号'")
    private String fileId;

    @ApiModelProperty(name = "linkId", value = "链接编号", dataType = "string", hidden = true)
    @Column(name = "link_id", columnDefinition = "VARCHAR(32) COMMENT '链接编号'")
    private String linkId;

    @ApiModelProperty(name = "viewId", value = "视频编号", dataType = "string", hidden = true)
    @Column(name = "view_id", columnDefinition = "VARCHAR(32) COMMENT '视频编号'")
    private String viewId;

    @ApiModelProperty(name = "audioId", value = "音频编号", dataType = "string", hidden = true)
    @Column(name = "audio_id", columnDefinition = "VARCHAR(32) COMMENT '音频编号'")
    private String audioId;

    @NotNull(message = "资料名称不为空")
    @ApiModelProperty(name = "datumName", dataType = "string", value = "资料名称", notes = "资料名称", required = true)
    @Column(name = "datum_name", columnDefinition = "VARCHAR(60) COMMENT '资料名称'")
    @JsonView(View.Summary.class)
    private String datumName;

    @ApiModelProperty(name = "datumArea", dataType = "string", value = "资料领域", notes = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例", required = true)
    @Column(name = "datum_area", columnDefinition = "INT COMMENT '资料领域：1教案2课件 3预习参考 4教学参考 5授课案例'")
    @JsonView(View.SummaryExtend.class)
    private Integer datumArea;

    @NotNull(message = "资料类型不为空")
    @JsonView(View.SummaryExtend.class)
    @ApiModelProperty(name = "datumType", dataType = "string", value = "资料类型", notes = "资料类型 1文档　2图册　3视频　4音频　5链接", required = true)
    @Column(name = "datum_type", columnDefinition = "INT COMMENT '资料类型 1文档　2图册　3视频　4音频　5链接'")
    private Integer datumType;

    @ApiModelProperty(name = "remark", value = "备注说明", dataType = "string", notes = "备注说明")
    @Column(name = "remark", columnDefinition = "VARCHAR(255) COMMENT '备注说明'")
    @JsonView(View.SummaryExtend.class)
    private String remark;

}
