package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　文章详情
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 15:56
 */
@Data
@Entity
@Table(name = "ad_txt", indexes = {@Index(columnList = "ad_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "ad_txt", comment = "文章详情")
public class AdTxt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "txt_id", columnDefinition = "varchar(32) COMMENT '广告文本id'")
    private String txtId;

    @Column(name = "ad_id", columnDefinition = "varchar(32) COMMENT '广告id'")
    private String adId;

    @Column(name = "ad_text", columnDefinition = "text(65535) COMMENT '广告文本内容'")
    private String adText;

}
