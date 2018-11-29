package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
        @Index(columnList = "chapter_id"),
        @Index(columnList = "sort_img_id"),
        @Index(columnList = "file_id"),
        @Index(columnList = "link_id"),
        @Index(columnList = "view_id"),
        @Index(columnList = "audio_id")
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
    private String dataId;

    @ApiModelProperty(name = "courseId", value = "科目编号", dataType = "string", required = true)
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程科目ID'")
    private String courseId;

    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string")
    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    private String chapterId;

    @ApiModelProperty(name = "sortImgId", value = "图册编号", dataType = "string")
    @Column(name = "sort_img_id", columnDefinition = "VARCHAR(32) COMMENT '图册编号'")
    private String sortImgId;

    @ApiModelProperty(name = "fileId", value = "文件编号", dataType = "string")
    @Column(name = "file_id", columnDefinition = "VARCHAR(32) COMMENT '文件编号'")
    private String fileId;

    @ApiModelProperty(name = "linkId", value = "链接编号", dataType = "string")
    @Column(name = "link_id", columnDefinition = "VARCHAR(32) COMMENT '链接编号'")
    private String linkId;

    @ApiModelProperty(name = "viewId", value = "视频编号", dataType = "string")
    @Column(name = "view_id", columnDefinition = "VARCHAR(32) COMMENT '视频编号'")
    private String viewId;

    @ApiModelProperty(name = "audioId", value = "音频编号", dataType = "string")
    @Column(name = "audio_id", columnDefinition = "VARCHAR(32) COMMENT '音频编号'")
    private String audioId;

    @ApiModelProperty(name = "datumName", dataType = "string", value = "资料名称", notes = "资料名称", required = true)
    @Column(name = "datum_name", columnDefinition = "VARCHAR(60) COMMENT '资料名称'")
    private String datumName;

    @ApiModelProperty(name = "datumArea", dataType = "string", value = "资料领域", notes = "资料领域：1教案 2课件 3预习参考 4教学参考 5授课案例", required = true)
    @Column(name = "datum_area", columnDefinition = "INT COMMENT '资料领域：1教案2课件 3预习参考 4教学参考 5授课案例'")
    private Integer datumArea;

    @ApiModelProperty(name = "datumType", dataType = "string", value = "资料类型", notes = "资料类型 1文档　2图册　3视频　4音频　5链接", required = true)
    @Column(name = "datum_type", columnDefinition = "INT COMMENT '资料类型 1文档　2图册　3视频　4音频　5链接'")
    private Integer datumType;

    @ApiModelProperty(name = "remark", value = "备注说明", dataType = "string", notes = "备注说明")
    @Column(name = "remark", columnDefinition = "VARCHAR(255) COMMENT '备注说明'")
    private String remark;

}
