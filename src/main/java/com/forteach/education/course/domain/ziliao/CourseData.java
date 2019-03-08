package com.forteach.education.course.domain.ziliao;

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
@Table(name = "course_data", indexes = {
        @Index(columnList = "data_id", name = "data_id_index"),
        @Index(columnList = "chapter_id", name = "chapter_id_index")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "course_data", comment = "章节资料")
@ApiModel(value = "章节资料库")
@AllArgsConstructor
@NoArgsConstructor
public class CourseData extends Entitys implements Serializable {

    @Id
    @Column(name = "data_id", columnDefinition = "VARCHAR(32) COMMENT '资料编号'")
    private String dataId;

    @Column(name = "datum_name", columnDefinition = "VARCHAR(60) COMMENT '资料名称'")
    private String datumName;

    @Column(name = "datum_ext", columnDefinition = "VARCHAR(60) COMMENT '资料扩展名'")
    private String datumExt;

    @Column(name = "datum_url", columnDefinition = "VARCHAR(255) COMMENT '资料URL'")
    private String datumUrl;

    @Column(name = "datum_area", columnDefinition = "VARCHAR(20) COMMENT '资料领域：3预习参考 4课堂参考 5授课案例、6复习参考'")
    private String datumArea;

    @Column(name = "datum_type", columnDefinition = "VARCHAR(3) COMMENT '资料类型 1文档　3视频　4音频　5链接'")
    private String datumType;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程科目ID'")
    private String courseId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    private String chapterId;

    @Column(name = "k_node_Id", columnDefinition = "CHAR(32) COMMENT '知识点编号'")
    private String kNodeId;

    @Column(name = "teach_share", columnDefinition = "CHAR(1) COMMENT '教师共享 0不共享 1共享'")
    private String teachShare = "0";

    @Column(name = "stu_share", columnDefinition = "CHAR(1) COMMENT '学生共享 0不共享 1共享'")
    private String stuShare = "0";

}
