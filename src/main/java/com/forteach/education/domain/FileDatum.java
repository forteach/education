package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:28
 * @Version: 1.0
 * @Description: 文档资料库
 */
@Data
@Entity
@Table(name = "file_datum", indexes = {@Index(columnList = "file_id"), @Index(columnList = "chapter_id")})
@IdClass(FileDatumFundPrimarykey.class)
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "file_datum", comment = "文档资料库")
public class FileDatum extends Entitys implements Serializable {

    @EmbeddedId
    private FileDatumFundPrimarykey fileDatumFundPrimarykey;

    @Id
    private String fileId;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    private String chapterId;
    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    private String fileName;
    @Column(name = "file_type", columnDefinition = "VARCHAR(10) COMMENT '文件类型'")
    private String fileType;
    @Column(name = "file_url", columnDefinition = "VARCHAR(255) COMMENT '文件URL'")
    private String fileUrl;
}
