package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　图片
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 15:45
 */
@Data
@Entity
@Table(name = "photos", indexes = {@Index(columnList = "sort_img_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "photos", comment = "图片")
public class Photos extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "photo_id", columnDefinition = "varchar(32) COMMENT '图片编号'")
    private String photoId;

    @Column(name = "sort_img_id", columnDefinition = "varchar(32) COMMENT '图片id'")
    private String sortImgId;

    @Column(name = "photo_name", columnDefinition = "varchar(60) COMMENT '图片名称'")
    private String photoName;

    @Column(name = "photo_src", columnDefinition = "varchar(255) COMMENT '图片路径'")
    private String photoSrc;

    @Column(name = "photo_description", columnDefinition = "varchar(255) COMMENT '图片描述'")
    private String photoDescription;

    @Column(name = "upload_time", columnDefinition = "datetime COMMENT '图片上传时间'")
    private String uploadTime;

}
