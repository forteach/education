package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:44
 * @Version: 1.0
 * @Description: 视频资料库
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "view_datum", indexes = {@Index(columnList = "view_id"), @Index(columnList = "chapter_id")})
@IdClass(ViewDatumFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "view_datum", comment = "视频资料库")
public class ViewDatum extends Entitys implements Serializable {

    private ViewDatumFundPrimarykey viewDatumFundPrimarykey;

    @Id
    private String viewId;

    private String chapterId;

    @Column(name = "view_name", columnDefinition = "VARCHAR(32) COMMENT '视频名称'")
    private String viewName;

    @Column(name = "view_type", columnDefinition = "VARCHAR(10) COMMENT '视频类型'")
    private String viewType;

    @Column(name = "view_url", columnDefinition = "VARCHAR(255) COMMENT '视频URL'")
    private String viewUrl;
}
