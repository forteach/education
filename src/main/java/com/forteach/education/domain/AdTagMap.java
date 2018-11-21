package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　广告标签对应表
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 14:49
 */
@Data
@Entity
@Table(name = "ad_tag_map", indexes = {@Index(columnList = "ad_id"), @Index(columnList = "tag_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(AdTagMapFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "ad_tag_map", comment = "广告标签对应表")
public class AdTagMap extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private AdTagMapFundPrimarykey adTagMapFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String adId;

    private String tagId;

}
