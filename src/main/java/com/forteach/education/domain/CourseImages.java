package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "课程科目轮播图")
@Table(name = "course_images", indexes = {@Index(columnList = "image_id"), @Index(columnList = "course_id")})
@org.hibernate.annotations.Table(appliesTo = "course_images", comment = "课程轮播图")
public class CourseImages extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "图片ID", name = "imageId", dataType = "string")
    @Column(name = "image_id", columnDefinition = "VARCHAR(32) COMMENT '图片编号ID'")
    private String imageId;

    @ApiModelProperty(value = "图片名称", name = "imageName", dataType = "string")
    @Column(name = "image_name", columnDefinition = "VARCHAR(32) COMMENT '图片名称'")
    private String imageName;

    @ApiModelProperty(value = "图片url", name = "imageUrl", dataType = "string")
    @Column(name = "image_url", columnDefinition = "VARCHAR(255) COMMENT '图片URL'")
    private String imageUrl;

    @ApiModelProperty(name = "courseId", value = "课程ID", dataType = "string")
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程科目ID'")
    private String courseId;

    @ApiModelProperty(value = "图册顺序", name = "indexNum", dataType = "int", notes = "轮播图排序,默认１")
    @Column(name = "index_num", columnDefinition = "INT(11) COMMENT '顺序ID'")
    private Integer indexNum;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
    }
}
