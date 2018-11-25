package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
@ApiModel(value = "广告")
@AllArgsConstructor
@NoArgsConstructor
public class Ad extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ad_id", columnDefinition = "varchar(32) COMMENT '广告ID'")
    @ApiModelProperty(name = "adId", value = "广告ID", dataType = "string")
    private String adId;

    @ApiModelProperty(name = "positionId", value = "广告所处的广告", dataType = "string")
    @Column(name = "position_id", columnDefinition = "varchar(32) COMMENT '广告所处的广告'")
    private String positionId;

    @ApiModelProperty(name = "mediaType", value = "广告类型", dataType = "string")
    @Column(name = "media_type", columnDefinition = "char(1) COMMENT '广告类型'")
    private String mediaType;

    @ApiModelProperty(name = "adName", value = "广告名称", dataType = "string")
    @Column(name = "ad_name", columnDefinition = "varchar(60) COMMENT '广告名称'")
    private String adName;

    @ApiModelProperty(name = "adLink", value = "广告链接地址", dataType = "string")
    @Column(name = "ad_link", columnDefinition = "varchar(255) COMMENT '广告链接地址'")
    private String adLink;

    @Temporal(value = TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "startTime", value = "开始时间", dataType = "date")
    @Column(name = "start_time", columnDefinition = "datetime COMMENT '开始时间'")
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    @ApiModelProperty(name = "endTime", value = "结束时间", dataType = "date")
    @Column(name = "end_time", columnDefinition = "datetime COMMENT '结束时间'")
    private Date endTime;

    @ApiModelProperty(name = "linkMan", value = "广告联系人", dataType = "string")
    @Column(name = "link_man", columnDefinition = "varchar(60) COMMENT '广告联系人'")
    private String linkMan;

    @ApiModelProperty(name = "linkEmail", value = "联系人邮箱", dataType = "string")
    @Column(name = "link_email", columnDefinition = "varchar(60) COMMENT '联系人邮箱'")
    private String linkEmail;

    @ApiModelProperty(name = "linkPhone", value = "联系人电话", dataType = "string")
    @Column(name = "link_phone", columnDefinition = "varchar(60) COMMENT '联系人电话'")
    private String linkPhone;

    @ApiModelProperty(name = "clickCount", value = "点击次数", dataType = "int")
    @Column(name = "click_count", columnDefinition = "int(15) COMMENT '点击次数'")
    private Integer clickCount;

    @ApiModelProperty(name = "titleImage", value = "广告标题图片", dataType = "string")
    @Column(name = "title_image", columnDefinition = "varchar(255) COMMENT '广告标题图片'")
    private String titleImage;

}
