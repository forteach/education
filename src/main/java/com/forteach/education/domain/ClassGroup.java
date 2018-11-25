package com.forteach.education.domain;

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
@ApiModel(value = "班级分组")
@AllArgsConstructor
@NoArgsConstructor
public class ClassGroup extends Entitys implements Serializable {

    @EmbeddedId
    @ApiModelProperty(value = "班级分组主键", hidden = true)
    private ClassGroupFundPrimarykey classGroupFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "小组编号", name = "groupId", dataType = "string")
    private String groupId;

    @ApiModelProperty(value = "班级编号", name = "classId", dataType = "string", required = true)
    private String classId;

    @ApiModelProperty(value = "小组名称", name = "groupName", dataType = "string", required = true)
    @Column(name = "group_name", columnDefinition = "VARCHAR(60) COMMENT '小组名称'")
    private String groupName;

    @ApiModelProperty(value = "小组得分", name = "groupScore", dataType = "int")
    @Column(name = "group_score", columnDefinition = "INT COMMENT '小组得分'")
    private Integer groupScore;

    @ApiModelProperty(value = "小组创建人", name = "groupUser", dataType = "string", required = true)
    @Column(name = "group_user", columnDefinition = "VARBINARY(32) COMMENT '小组创建人'")
    private String groupUser;

    @ApiModelProperty(value = "小组组长", name = "groupLeader", dataType = "string", required = true)
    @Column(name = "group_leader", columnDefinition = "VARCHAR(32) COMMENT '小组组长'")
    private String groupLeader;

    @ApiModelProperty(value = "组员数量", name = "groupNum", dataType = "int")
    @Column(name = "group_num", columnDefinition = "INT COMMENT '组员数量'")
    private Integer groupNum;

}
