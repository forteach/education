package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @Description: 资料图片库　展示方式 0->仅主人可见,1->输入密码即可查看,2->仅组成员能查看,3->回答问题即可查看''
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 14:24
 */
@Data
@Entity
@Table(name = "photo_sort",indexes = {@Index(columnList = "teacher_id"),@Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "photo_sort", comment = "资料图片库")
public class PhotoSort extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "sort_img_id", columnDefinition = "varchar(32) COMMENT '分类id uuid'")
    private String sortImgId;

    @Column(name = "teacher_id", columnDefinition = "varchar(32) COMMENT '老师id'")
    private String teacherId;

    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @Column(name = "sort_img_name", columnDefinition = "varchar(40) COMMENT '分类名称'")
    private String sortImgName;

    @Column(name = "sort_img_type", columnDefinition = "int(11) COMMENT '展示方式'")
    private Integer sortImgType;

    @Column(name = "img_password", columnDefinition = "varchar(6) COMMENT '查看密码'")
    private String imgPassword;

    @Column(name = "top_pic_src", columnDefinition = "varchar(255) COMMENT '封面图片的路径'")
    private String topPicSrc;


}
