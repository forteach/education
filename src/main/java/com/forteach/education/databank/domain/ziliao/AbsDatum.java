package com.forteach.education.databank.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:28
 * @Version: 1.0
 * @Description: 文档资料库
 */

@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsDatum extends Entitys {

    @Id
    @Column(name = "file_id", columnDefinition = "VARCHAR(32) COMMENT '文件编号'")
    public String fileId;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    public String courseId;

    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '章节编号'")
    public String chapterId;

    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    public String fileName;

    @Column(name = "file_type", columnDefinition = "VARCHAR(10) COMMENT '文件类型'")
    public String fileType;

    @Column(name = "file_url", columnDefinition = "VARCHAR(255) COMMENT '文件URL'")
    public String fileUrl;

    @Column(name = "datum_type", columnDefinition = "VARCHAR(32) COMMENT '资料类型 1文档　2图册　3视频　4音频　5链接'")
    private String datumType;

    @Column(name = "k_node_id", columnDefinition = "VARCHAR(32) COMMENT '所属单知识点'")
    private String kNodeId;

    @Column(name = "datum_area", columnDefinition = "VARCHAR(32) COMMENT '资料领域：1教案 2课件1 3预习参考 4课堂参考 5授课案例、6复习参考'")
    private String datumArea;

    @Column(name = "teach_share", columnDefinition = "CHAR(1) COMMENT '教师共享 0不共享 1共享'")
    private String teachShare = "0";

    @Column(name = "stu_share", columnDefinition = "CHAR(1) COMMENT '学生共享 0不共享 1共享'")
    private String stuShare = "0";

}
