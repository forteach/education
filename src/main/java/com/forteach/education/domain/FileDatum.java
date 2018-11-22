package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "文件数据信息", value = "文件数据")
@org.hibernate.annotations.Table(appliesTo = "file_datum", comment = "文档资料库")
public class FileDatum extends Entitys implements Serializable {

    @EmbeddedId
    @ApiModelProperty(value = "文件数据主键", hidden = true)
    private FileDatumFundPrimarykey fileDatumFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "fileId", value = "文件编号")
    private String fileId;

    @ApiModelProperty(value = "科目编号", name = "courseId")
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    @ApiModelProperty(value = "chapterId", required = true, name = "章节编号")
    private String chapterId;

    @ApiModelProperty(value = "文件名称", required = true, name = "fileName")
    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    private String fileName;

    @ApiModelProperty(value = "文件类型", required = true, name = "fileType")
    @Column(name = "file_type", columnDefinition = "VARCHAR(10) COMMENT '文件类型'")
    private String fileType;

    @ApiModelProperty(value = "文件URL", required = true, name = "fileUrl")
    @Column(name = "file_url", columnDefinition = "VARCHAR(255) COMMENT '文件URL'")
    private String fileUrl;
}
