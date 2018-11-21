package com.forteach.education.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@IdClass(ChapteDataFundPrimarykey.class)
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
public class ChapteData extends Entitys implements Serializable {

    @EmbeddedId
    private ChapteDataFundPrimarykey chapteDataFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "资料编号", value = "dataId")
    private String dataId;

    @ApiModelProperty(value = "courseId", name = "科目编号")
    private String courseId;

    @ApiModelProperty(name = "章节编号", value = "chapterId")
    private String chapterId;

    @ApiModelProperty(value = "sortImgId", name = "图册编号")
    private String sortImgId;

    @ApiModelProperty(value = "fileId", name = "文件编号")
    private String fileId;

    @ApiModelProperty(value = "linkId", name = "链接编号")
    private String linkId;

    @ApiModelProperty(value = "viewId", name = "视频编号")
    private String viewId;

    @ApiModelProperty(value = "audioId", name = "音频编号")
    private String audioId;

    @ApiModelProperty(value = "datumName", name = "资料名称", notes = "资料名称")
    @Column(name = "datum_name", columnDefinition = "VARCHAR(60) COMMENT '资料名称'")
    private String datumName;

    @ApiModelProperty(value = "datumArea", name = "资料领域", notes = "资料领域：１教案２课件３预习参考４教学参考５授课案例")
    @Column(name = "datum_area", columnDefinition = "INT COMMENT '资料领域：１教案２课件３预习参考４教学参考５授课案例'")
    private Integer datumArea;

    @ApiModelProperty(value = "datumType", name = "资料类型", notes = "资料类型 1文档　2图册　3视频　4音频　5链接")
    @Column(name = "datum_type", columnDefinition = "INT COMMENT '资料类型 1文档　2图册　3视频　4音频　5链接'")
    private Integer datumType;

    @ApiModelProperty(value = "remark", name = "备注说明", dataType = "string", notes = "备注说明")
    @Column(name = "remark", columnDefinition = "VARCHAR(255) COMMENT '备注说明'")
    private String remark;

}
