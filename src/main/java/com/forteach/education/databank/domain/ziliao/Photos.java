package com.forteach.education.databank.domain.ziliao;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import lombok.*;
import javax.persistence.*;;
import java.io.Serializable;


/**
 * @Description:　图片
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 15:45
 */
@Data
@Entity
//@Builder
@Table(name = "photos")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "photos", comment = "图片")
@ApiModel(value = "图集图片")
//@AllArgsConstructor
//@NoArgsConstructor
public class Photos extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "photo_id", columnDefinition = "varchar(32) COMMENT '图片编号'")
    private String photoId;

    @Column(name = "photo_name", columnDefinition = "varchar(60) COMMENT '图片名称'")
    private String name;

    @Column(name = "photo_src", columnDefinition = "varchar(255) COMMENT '图片路径'")
    private String src;

    @Column(name = "photo_description", columnDefinition = "varchar(255) COMMENT '图片描述'")
    private String description;

}
