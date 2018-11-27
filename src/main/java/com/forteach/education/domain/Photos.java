package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:　图片
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 15:45
 */
@Data
@Entity
@Builder
@Table(name = "photos", indexes = {@Index(columnList = "sort_img_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "photos", comment = "图片")
@ApiModel(value = "图片")
@AllArgsConstructor
@NoArgsConstructor
public class Photos extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "photo_id", columnDefinition = "varchar(32) COMMENT '图片编号'")
    @ApiModelProperty(value = "图片编号", name = "photoId", dataType = "string")
    private String photoId;

    @ApiModelProperty(name = "sortImgId", value = "图片id", dataType = "string", required = true)
    @Column(name = "sort_img_id", columnDefinition = "varchar(32) COMMENT '图片id'")
    private String sortImgId;

    @ApiModelProperty(value = "图片名称", name = "photoName", dataType = "string")
    @Column(name = "photo_name", columnDefinition = "varchar(60) COMMENT '图片名称'")
    private String photoName;

    @ApiModelProperty(value = "图片路径", name = "photoSrc", dataType = "string")
    @Column(name = "photo_src", columnDefinition = "varchar(255) COMMENT '图片路径'")
    private String photoSrc;

    @ApiModelProperty(value = "图片描述", name = "photoDescription", dataType = "string")
    @Column(name = "photo_description", columnDefinition = "varchar(255) COMMENT '图片描述'")
    private String photoDescription;

    @ApiModelProperty(value = "图片上传时间", name = "uploadTime", dataType = "string", hidden = true)
    @Column(name = "upload_time", columnDefinition = "datetime COMMENT '图片上传时间'")
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    private Date uploadTime;

}
