package com.forteach.education.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
@Builder
@Table(name = "photo_sort",indexes = {@Index(columnList = "course_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "photo_sort", comment = "资料图片库")
@ApiModel(value = "资料图片库")
@AllArgsConstructor
@NoArgsConstructor
public class PhotoSort extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "sort_img_id", columnDefinition = "varchar(32) COMMENT '图册分类id uuid'")
    @ApiModelProperty(value = "图册分类id", name = "sortImgId", dataType = "string", readOnly = true, hidden = true)
    private String sortImgId;

//    @Column(name = "teacher_id", columnDefinition = "varchar(32) COMMENT '老师id'")
//    private String teacherId;

    @ApiModelProperty(value = "科目编号", name = "courseId", required = true)
    @Column(name = "course_id", columnDefinition = "varchar(32) COMMENT '科目编号'")
    private String courseId;

    @ApiModelProperty(value = "分类名称", name = "sortImgName", dataType = "string",required = true)
    @Column(name = "sort_img_name", columnDefinition = "varchar(40) COMMENT '分类名称'")
    private String sortImgName;

    @ApiModelProperty(value = "展示方式", name = "sortImgType", dataType = "int", required = true)
    @Column(name = "sort_img_type", columnDefinition = "int(11) COMMENT '展示方式'")
    private Integer sortImgType;

    @JsonIgnore
    @ApiModelProperty(value = "查看密码", name = "imgPassword", dataType = "string")
    @Column(name = "img_password", columnDefinition = "varchar(6) COMMENT '查看密码'")
//    @ColumnTransformer(read = "decrypt(img_password),", write = "encrypt(nvl(?, 'null'))")
    private String imgPassword;

//    @URL(message = "不是一个URL")
    @ApiModelProperty(value = "封面图片的路径", name = "topPicSrc", dataType = "string",required = true)
    @Column(name = "top_pic_src", columnDefinition = "varchar(255) COMMENT '封面图片的路径'")
    private String topPicSrc;


}
