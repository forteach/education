package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 16:16
 */
@Data
@Entity
@Table(name = "article_txt",indexes = {@Index(columnList = "article_id")})
@EqualsAndHashCode(callSuper = true)
public class ArticleTxt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "txt_id", columnDefinition = "varchar(32) COMMENT '文章详情ID'")
    private String txtId;

    @Column(name = "article_id", columnDefinition = "varchar(32) COMMENT '文章编号'")
    private String articleId;

    @Column(name = "article_conten", columnDefinition = "text(65535) COMMENT '文章内容'")
    private String articleConten;

}
