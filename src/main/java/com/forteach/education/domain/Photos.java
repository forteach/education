package com.forteach.education.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @JsonView(View.SummaryExtend.class)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "photo_id", columnDefinition = "varchar(32) COMMENT '图片编号'")
    @ApiModelProperty(value = "图片编号", name = "photoId", dataType = "string")
    private String photoId;

    @JsonView(View.Summary.class)
    @NotNull(message = "图集册ID不为空")
    @ApiModelProperty(name = "sortImgId", value = "图片id", dataType = "string", required = true)
    @Column(name = "sort_img_id", columnDefinition = "varchar(32) COMMENT '图片id'")
    private String sortImgId;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "图片名称", name = "photoName", dataType = "string")
    @Column(name = "photo_name", columnDefinition = "varchar(60) COMMENT '图片名称'")
    private String photoName;

//    @URL(message = "不是一个URL")
    @JsonView(View.Summary.class)
    @NotNull(message = "图片路径不为空")
    @ApiModelProperty(value = "图片路径", name = "photoSrc", dataType = "string", required = true)
    @Column(name = "photo_src", columnDefinition = "varchar(255) COMMENT '图片路径'")
    private String photoSrc;

    @ApiModelProperty(value = "图片描述", name = "photoDescription", dataType = "string")
    @Column(name = "photo_description", columnDefinition = "varchar(255) COMMENT '图片描述'")
    @JsonView(View.Summary.class)
    private String photoDescription;

    @ApiModelProperty(value = "图片上传时间", name = "uploadTime", dataType = "string", hidden = true, example = "2018-12-05 14:56:45")
    @Column(name = "upload_time", columnDefinition = "datetime COMMENT '图片上传时间'")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(View.SummaryExtend.class)
    @LastModifiedDate
    private Date uploadTime;

}
