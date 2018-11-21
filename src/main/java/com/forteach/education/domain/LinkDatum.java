package com.forteach.education.domain;

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
 * @Date: 2018/11/17 14:08
 * @Version: 1.0
 * @Description: 链接资料库
 */
@Builder
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
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "链接ID", value = "linkId")
    private String linkId;

    @ApiModelProperty(name = "章节编号", value = "chapterId")
    private String chapterId;

    @ApiModelProperty(name = "链接URL", value = "linkUrl")
    @Column(name = "link_url", columnDefinition = "VARCHAR(255) COMMENT 'URL'")
    private String linkUrl;

    @ApiModelProperty(name = "链接名称", value = "linkName")
    @Column(name = "link_name", columnDefinition = "VARCHAR(60) COMMENT '链接名称'")
    private String linkName;
}
