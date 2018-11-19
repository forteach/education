package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　文章标签对应表
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 16:33
 */
@Data
@Entity
@Table(name = "article_tag_map", indexes = {@Index(columnList = "article_id"), @Index(columnList = "tagId")})
@EqualsAndHashCode(callSuper = true)
@IdClass(ArticleTagMapFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "article_tag_map", comment = "文章标签对应表")
public class ArticleTagMap extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ArticleTagMapFundPrimarykey articleTagMapFundPrimarykey;

    private String articleId;

    private String tagId;


}