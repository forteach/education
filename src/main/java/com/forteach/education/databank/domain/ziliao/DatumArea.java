package com.forteach.education.databank.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 课程章节资料领域，与课程章节资料一对多关系
 */
@Data
@Entity
@Builder
@Table(name = "datum_area", indexes = {@Index(columnList = "chapter_id", name = "chapter_id_index"),
        @Index(columnList = "datum_area", name = "datum_area_index"), @Index(columnList = "k_node_id", name = "k_node_id_index")})
@org.hibernate.annotations.Table(appliesTo = "datum_area", comment = "资料领域对照表")
@IdClass(DatumAreaPk.class)
@ApiModel(value = "章节资料领域对照表")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DatumArea extends Entitys implements Serializable {

    @EmbeddedId
    private DatumAreaPk datumAreaPk;

    /**
     * 课程资料编号
     */
    public String fileId;

    /**
     * 资料领域：1教案 2课件 3预习参考 4课堂参考 5授课案例、6复习参考'
     */
    private String datumArea;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程编号'")
    public String courseId;

    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '章节编号'")
    public String chapterId;

    @Column(name = "datum_type", columnDefinition = "VARCHAR(32) COMMENT '资料类型 1文档　　3视频　4音频　5链接'")
    private String datumType;

    @Column(name = "k_node_id", columnDefinition = "VARCHAR(32) COMMENT '所属单知识点'")
    private String kNodeId;

}
