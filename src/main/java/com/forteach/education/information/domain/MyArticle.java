package com.forteach.education.information.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 我收藏和发布的文章
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "my_article", indexes = {
        @Index(name = "pk_Id_index", columnList = "pk_id")
})
@org.hibernate.annotations.Table(appliesTo = "my_article", comment = "我的文章")
public class MyArticle extends Entitys implements Serializable {

    @Id
    @Column(name = "pk_id", nullable = false, columnDefinition = "VARCHAR(40) COMMENT '主键id'")
    private String pkId;

    @Column(name = "article_id", nullable = false, columnDefinition = "VARCHAR(40) COMMENT '文章id'")
    private String articleId;

    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(40) COMMENT '用户id'")
    private String userId;

    /**
     * 我的文章标签类型 0：我发布的 1：我收藏的 2:点赞
     */
    @Column(name = "tag_type", nullable = false, columnDefinition = "VARCHAR(32) COMMENT '我的文章标签类型 0：我发布的 1：我收藏的 2:点赞'")
    private int tagType;

    public MyArticle() {
    }

    public MyArticle(String pkId) {
        this.pkId = pkId;
    }
}