package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Table(name = "classes", indexes = {@Index(columnList = "class_id")})
@org.hibernate.annotations.Table(appliesTo = "classes", comment = "班级")
public class Classes extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "class_id", columnDefinition = "VARCHAR(32) COMMENT '班级编号'")
    private String classId;

    @Column(name = "class_name", columnDefinition = "VARCHAR(60) COMMENT '班级名称'")
    private String className;
}
