package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "链接资料库")
public class LinkDatum extends Entitys implements Serializable {

    @EmbeddedId
    @ApiModelProperty(value = "链接资料库", hidden = true)
    private LinkDatumFundPrimarykey linkDatumFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "链接ID", name = "linkId", dataType = "string")
    private String linkId;

    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string")
    private String chapterId;

    @ApiModelProperty(value = "链接URL", name = "linkUrl", dataType = "string", required = false)
    @Column(name = "link_url", columnDefinition = "VARCHAR(255) COMMENT 'URL'")
    private String linkUrl;

    @ApiModelProperty(value = "链接名称", name = "linkName", dataType = "string", required = true)
    @Column(name = "link_name", columnDefinition = "VARCHAR(60) COMMENT '链接名称'")
    private String linkName;
}
