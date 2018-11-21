package com.forteach.education.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:28
 * @Version: 1.0
 * @Description: 文档资料库
 */
@Builder
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
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "文件编号", value = "fileId")
    private String fileId;

    @ApiModelProperty(name = "科目编号", value = "courseId")
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    @ApiModelProperty(name = "chapterId", value = "章节编号")
    private String chapterId;

    @ApiModelProperty(name = "文件名称", value = "fileName")
    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    private String fileName;

    @ApiModelProperty(name = "文件类型", value = "fileType")
    @Column(name = "file_type", columnDefinition = "VARCHAR(10) COMMENT '文件类型'")
    private String fileType;

    @ApiModelProperty(name = "文件URL", value = "fileUrl")
    @Column(name = "file_url", columnDefinition = "VARCHAR(255) COMMENT '文件URL'")
    private String fileUrl;
}
