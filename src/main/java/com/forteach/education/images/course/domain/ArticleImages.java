package com.forteach.education.images.course.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-29 14:33
 * @Version: 1.0
 * @Description: 课程首页轮播图信息
 */
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "article_images", indexes = {
        @Index(columnList = "article_id", name = "art_id_index")
})
@org.hibernate.annotations.Table(appliesTo = "article_images", comment = "资讯内容图片")
public class ArticleImages extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "image_id", columnDefinition = "VARCHAR(32) COMMENT '图片编号ID'")
    private String imageId;

    @Column(name = "image_name", columnDefinition = "VARCHAR(128) COMMENT '图片名称'")
    private String imageName;


    @Column(name = "image_url", columnDefinition = "VARCHAR(255) COMMENT '图片URL'")
    private String imageUrl;

    @Column(name = "article_id", columnDefinition = "VARCHAR(32) COMMENT '资讯ID'")
    private String articleId;

    @ApiModelProperty(value = "图册顺序", name = "indexNum", dataType = "int", notes = "图片排序,默认１")
    @Column(name = "index_num", columnDefinition = "INT(11) COMMENT '顺序ID'")
    private Integer indexNum;

}
