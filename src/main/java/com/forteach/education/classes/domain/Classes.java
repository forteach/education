package com.forteach.education.classes.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 14:25
 * @Version: 1.0
 * @Description: 班级
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "classes", indexes = {@Index(columnList = "class_id", name = "class_id_index")})
@org.hibernate.annotations.Table(appliesTo = "classes", comment = "班级")
@ApiModel(value = "班级")
@AllArgsConstructor
@NoArgsConstructor
public class Classes extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "class_id", columnDefinition = "VARCHAR(32) COMMENT '班级编号'")
    @ApiModelProperty(value = "班级编号", name = "classId", dataType = "string")
    private String classId;

    @ApiModelProperty(value = "班级名称", name = "className", dataType = "string", required = true)
    @Column(name = "class_name", columnDefinition = "VARCHAR(60) COMMENT '班级名称'", nullable = false)
    private String className;

    @ApiModelProperty(value = "年级", name = "grade", dataType = "int")
    @Column(name = "grade", columnDefinition = "INT(11) COMMENT '年级'")
    private Long grade;

}
