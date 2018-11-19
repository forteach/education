package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 15:19
 */
@Data
@Entity
@Table(name = "ad", indexes = {@Index(columnList = "ad_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "ad", comment = "广告")
public class Ad extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ad_id", columnDefinition = "varchar(32) COMMENT '广告ID'")
    private String adId;

    @Column(name = "position_id", columnDefinition = "varchar(32) COMMENT '广告所处的广告'")
    private String positionId;

    @Column(name = "media_type", columnDefinition = "char(1) COMMENT '广告类型'")
    private String mediaType;

    @Column(name = "ad_name", columnDefinition = "varchar(60) COMMENT '广告名称'")
    private String adName;

    @Column(name = "ad_link", columnDefinition = "varchar(255) COMMENT '广告链接地址'")
    private String adLink;

    @Column(name = "start_time", columnDefinition = "datetime COMMENT '开始时间'")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "datetime COMMENT '结束时间'")
    private Date endTime;

    @Column(name = "link_man", columnDefinition = "varchar(60) COMMENT '广告联系人'")
    private String linkMan;

    @Column(name = "link_email", columnDefinition = "varchar(60) COMMENT '联系人邮箱'")
    private String linkEmail;

    @Column(name = "link_phone", columnDefinition = "varchar(60) COMMENT '联系人电话'")
    private String linkPhone;

    @Column(name = "click_count", columnDefinition = "int(15) COMMENT '点击次数'")
    private Integer clickCount;

    @Column(name = "title_image", columnDefinition = "varchar(255) COMMENT '广告标题图片'")
    private String titleImage;

}
