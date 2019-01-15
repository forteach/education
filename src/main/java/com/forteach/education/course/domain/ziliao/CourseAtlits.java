package com.forteach.education.course.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "course_atlits",indexes = {@Index(columnList = "chapter_id")})
@org.hibernate.annotations.Table(appliesTo = "course_atlits", comment = "图集信息")
@ApiModel(value = "图集信息")
@EqualsAndHashCode(callSuper = true)
public class CourseAtlits extends Entitys implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(32) COMMENT '图集编号'")
    private String id;

    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '章节编号'")
    public String chapterId;

    @Column(name = "file_name", columnDefinition = "VARCHAR(255) COMMENT '文件名称'")
    public String fileName;

    @Column(name = "file_url", columnDefinition = "VARCHAR(255) COMMENT '文件URL'")
    public String fileUrl="/img";

}
