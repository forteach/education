package com.forteach.education.databank.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:28
 * @Version: 1.0
 * @Description: 文档资料库
 */

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsDatum extends Entitys{

    @Id
    @Column(name = "file_id", columnDefinition = "VARCHAR(32) COMMENT '文件编号'")
    public String fileId;

    @Column(name = "data_id", columnDefinition = "VARCHAR(32) COMMENT '章节资料编号'")
    public String dataId;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    public String courseId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    public String chapterId;

    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    public String fileName;

    @Column(name = "file_type", columnDefinition = "VARCHAR(10) COMMENT '文件类型'")
    public String fileType;

    @Column(name = "file_url", columnDefinition = "VARCHAR(255) COMMENT '文件URL'")
    public String fileUrl;

    
}
