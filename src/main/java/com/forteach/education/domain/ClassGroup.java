package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 14:29
 * @Version: 1.0
 * @Description: 班级分组
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@IdClass(ClassGroupFundPrimarykey.class)
@Table(name = "class_group", indexes = {@Index(columnList = "group_id"), @Index(columnList = "class_id")})
@org.hibernate.annotations.Table(appliesTo = "class_group", comment = "班级分组")
public class ClassGroup extends Entitys implements Serializable {

    @EmbeddedId
    private ClassGroupFundPrimarykey classGroupFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String groupId;

    private String classId;

    @Column(name = "group_name", columnDefinition = "VARCHAR(60) COMMENT '小组名称'")
    private String groupName;

    @Column(name = "group_score", columnDefinition = "INT COMMENT '小组得分'")
    private Integer groupScore;

    @Column(name = "group_user", columnDefinition = "VARBINARY(32) COMMENT '小组创建人'")
    private String groupUser;

    @Column(name = "group_leader", columnDefinition = "VARCHAR(32) COMMENT '小组组长'")
    private String groupLeader;

    @Column(name = "group_num", columnDefinition = "INT COMMENT '组员数量'")
    private Integer groupNum;

}
