package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-8 16:22
 * @Version: 1.0
 * @Description: 文章
 */
@Data
@Entity
@Table(name = "article", indexes = {@Index(columnList = "article_id"), @Index(columnList = "sort_article_id")})
@EqualsAndHashCode(callSuper = true)
public class Article extends Entitys implements Serializable {

    @Id
    @Column(name = "article_id", columnDefinition = "varchar(32) COMMENT '文章编号'")
    private String articleId;

    @Column(name = "sort_article_id", columnDefinition = "varchar(32) COMMENT '分类编号'")
    private String sortArticleId;

    @Column(name = "article_name", columnDefinition = "varchar(128) COMMENT'文章名称'")
    private String articleName;

    @Column(name = "article_time", columnDefinition = "datetime COMMENT '发布时间'")
    private Date articleTime;

    @Column(name = "article_type", columnDefinition = "bit(1) COMMENT '文章模式'")
    private Boolean articleType;

    @Column(name = "title_image", columnDefinition = "varchar(255) COMMENT '标题图片'")
    private String titleImage;


}
