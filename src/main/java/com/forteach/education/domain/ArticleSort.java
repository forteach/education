package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

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
@org.hibernate.annotations.Table(appliesTo = "article_sort", comment = "文章分类")
public class ArticleSort extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "sort_article_id", columnDefinition = "varchar(32) COMMENT '分类编号'")
    private String sortArticleId;

    @Column(name = "sort_article_name", columnDefinition = "varchar(32) COMMENT '分类名称'")
    private String sortArticleName;


}
