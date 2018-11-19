package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 14:08
 * @Version: 1.0
 * @Description: 链接资料库
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@IdClass(LinkDatumFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "link_datum", comment = "链接资料库")
@Table(name = "link_datum", indexes = {@Index(columnList = "link_id"), @Index(columnList = "chapter_id")})
public class LinkDatum extends Entitys implements Serializable {

    @EmbeddedId
    private LinkDatumFundPrimarykey linkDatumFundPrimarykey;

    @Id
    private String linkId;

    private String chapterId;

    @Column(name = "link_url", columnDefinition = "VARCHAR(255) COMMENT 'URL'")
    private String linkUrl;

    @Column(name = "link_name", columnDefinition = "VARCHAR(60) COMMENT '链接名称'")
    private String linkName;
}
