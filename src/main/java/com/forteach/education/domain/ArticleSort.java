package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-8 16:02
 * @Version: 1.0
 * @Description: 文章分类
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "article_sort", indexes = {@Index(columnList = "sort_article_id")})
public class ArticleSort extends Entitys implements Serializable {

    @Id
    @Column(name = "sort_article_id", columnDefinition = "varchar(32) COMMENT '分类编号'")
    private String sortArticleId;

    @Column(name = "sort_article_name", columnDefinition = "varchar(32) COMMENT '分类名称'")
    private String sortArticleName;


}
