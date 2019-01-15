package com.forteach.education.databank.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
import com.forteach.education.course.domain.CourseShareUsersPk;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name = "datum_area" , indexes = {@Index(columnList = "chapter_id"),@Index(columnList = "datum_area"),@Index(columnList = "k_node_id")})
@org.hibernate.annotations.Table(appliesTo = "datum_area", comment = "资料领域对照表")
@IdClass(DatumAreaPk.class)
@ApiModel(value = "章节资料领域对照表")
@AllArgsConstructor
@NoArgsConstructor
public class DatumArea extends Entitys implements Serializable{

    @EmbeddedId
    @ApiModelProperty(value = "资料领域主键", hidden = true)
    private DatumAreaPk datumAreaPk;

    public String fileId;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程编号'")
    public String courseId;

    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '章节编号'")
    public String chapterId;

    //资料领域：1教案 2课件 3预习参考 4课堂参考 5授课案例、6复习参考'
    private String datumArea;

    @Column(name = "datum_type", columnDefinition = "VARCHAR(3) COMMENT '资料类型 1文档　2图册　3视频　4音频　5链接'")
    private String datumType;

    @Column(name = "k_node_id", columnDefinition = "VARCHAR(32) COMMENT '所属单知识点'")
    private String kNodeId;

    @Column(name = "data_id", columnDefinition = "VARCHAR(32) COMMENT '章节资料编号'")
    public String dataId;
}
