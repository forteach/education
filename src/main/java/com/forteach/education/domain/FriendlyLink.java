package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 15:03
 */
@Data
@Entity
@Table(name = "friendly_link")
@EqualsAndHashCode(callSuper = true)
public class FriendlyLink extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "link_id", columnDefinition = "varchar(32) COMMENT '连接id'")
    private String linkId;

    @Column(name = "link_name", columnDefinition = "varchar(60) COMMENT '友情链接名称'")
    private String linkName;

    @Column(name = "link_url", columnDefinition = "varchar(255) COMMENT '链接地址'")
    private String linkUrl;

    @Column(name = "link_logo", columnDefinition = "varchar(255) COMMENT 'LOGO图片'")
    private String linkLogo;

    @Column(name = "show_order", columnDefinition = "int(11) COMMENT '页面显示顺序'")
    private Integer showOrder;
}
