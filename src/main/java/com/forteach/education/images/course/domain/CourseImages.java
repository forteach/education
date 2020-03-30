package com.forteach.education.images.course.domain;

import com.forteach.education.common.domain.Entitys;
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
@Table(name = "course_images", indexes = {@Index(columnList = "image_id", name = "image_id_index"), @Index(columnList = "course_id", name = "course_id_index")})
@org.hibernate.annotations.Table(appliesTo = "course_images", comment = "课程轮播图")
public class CourseImages extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "image_id", columnDefinition = "VARCHAR(32) COMMENT '图片编号ID'")
    private String imageId;

    @Column(name = "image_name", columnDefinition = "VARCHAR(32) COMMENT '图片名称'")
    private String imageName;

    @Column(name = "image_url", columnDefinition = "VARCHAR(255) COMMENT '图片URL'")
    private String imageUrl;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程科目ID'")
    private String courseId;

    @Column(name = "index_num", columnDefinition = "INT(11) COMMENT '顺序ID'")
    private Integer indexNum;

}
