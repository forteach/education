package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　友情链接资讯
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 15:03
 */
@Data
@Entity
@Table(name = "friendly_link")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "friendly_link", comment = "友情链接资讯")
@ApiModel(value = "友情链接资讯")
public class FriendlyLink extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "link_id", columnDefinition = "varchar(32) COMMENT '连接id'")
    @ApiModelProperty(value = "链接id", name = "linkId", dataType = "string")
    private String linkId;

    @ApiModelProperty(value = "友情链接名称", name = "linkName", dataType = "string")
    @Column(name = "link_name", columnDefinition = "varchar(60) COMMENT '友情链接名称'")
    private String linkName;

    @ApiModelProperty(value = "链接地址", name = "linkUrl", required = true, dataType = "string")
    @Column(name = "link_url", columnDefinition = "varchar(255) COMMENT '链接地址'")
    private String linkUrl;

    @ApiModelProperty(value = "LOGO图片", name = "linkLogo", dataType = "string")
    @Column(name = "link_logo", columnDefinition = "varchar(255) COMMENT 'LOGO图片'")
    private String linkLogo;

    @ApiModelProperty(value = "页面显示顺序", name = "showOrder", dataType = "int")
    @Column(name = "show_order", columnDefinition = "int(11) COMMENT '页面显示顺序'")
    private Integer showOrder;
}
