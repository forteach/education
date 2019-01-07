package com.forteach.education.databank.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import lombok.*;
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
        @Index(columnList = "chapter_id")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "chapte_data", comment = "章节资料")
@ApiModel(value = "章节资料库")
@AllArgsConstructor
@NoArgsConstructor
public class ChapteData extends Entitys implements Serializable {

    @Id
    @Column(name = "data_id", columnDefinition = "VARCHAR(32) COMMENT '资料编号'")
    private String dataId;

    @Column(name = "datum_name", columnDefinition = "VARCHAR(60) COMMENT '资料名称'")
    private String datumName;

    @Column(name = "datum_area", columnDefinition = "VARCHAR(3) COMMENT '资料领域：1教案 2课件1 3预习参考 4课堂参考 5授课案例、6复习参考'")
    private String datumArea;

    @Column(name = "datum_type", columnDefinition = "VARCHAR(3) COMMENT '资料类型 1文档　2图册　3视频　4音频　5链接'")
    private String datumType;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程科目ID'")
    private String courseId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    private String chapterId;

    @Column(name = "k_node_Id", columnDefinition = "CHAR(32) COMMENT '知识点编号'")
    private String kNodeId;

}
