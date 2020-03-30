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
 * @Date: 2018/11/17 14:40
 * @Version: 1.0
 * @Description: 小组成员
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "group_member", indexes = {@Index(columnList = "group_id", name = "group_id_index"),
        @Index(columnList = "member_id", name = "member_id_index")})
@IdClass(GroupMemberFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "group_member", comment = "小组成员")
@ApiModel(value = "小组成员")
@AllArgsConstructor
@NoArgsConstructor
public class GroupMember extends Entitys implements Serializable {

    @EmbeddedId
    @ApiModelProperty(value = "小组成员主键", hidden = true)
    private GroupMemberFundPrimarykey groupMemberFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "小组编号", name = "groupId", dataType = "string")
    private String groupId;

    @ApiModelProperty(value = "组员编号", name = "memberId", dataType = "string", required = true)
    private String memberId;

    @ApiModelProperty(value = "组员姓名", name = "memberName", dataType = "string", required = true)
    @Column(name = "member_name", columnDefinition = "VARCHAR(20) COMMENT '组员姓名'")
    private String memberName;

    @ApiModelProperty(value = "组员性别", name = "memeberSex", dataType = "int", required = true)
    @Column(name = "member_sex", columnDefinition = "BIT COMMENT '组员性别'")
    private Integer memeberSex;

    @ApiModelProperty(value = "组员学习评级", name = "memeberLex", dataType = "string", required = true)
    @Column(name = "member_lex", columnDefinition = "INT COMMENT '组员学习评级'")
    private String memeberLex;
}
