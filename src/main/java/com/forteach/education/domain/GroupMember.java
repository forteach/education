package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Table(name = "group_member", indexes = {@Index(columnList = "group_id"), @Index(columnList = "member_id")})
@IdClass(GroupMemberFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "group_member", comment = "小组成员")
public class GroupMember extends Entitys implements Serializable {

    @EmbeddedId
    private GroupMemberFundPrimarykey groupMemberFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String groupId;

    private String memberId;

    @Column(name = "member_name", columnDefinition = "VARCHAR(20) COMMENT '组员姓名'")
    private String memberName;

    @Column(name = "member_sex", columnDefinition = "BIT COMMENT '组员性别'")
    private Integer memeberSex;

    @Column(name = "member_lex", columnDefinition = "INT COMMENT '组员学习评级'")
    private String memeberLex;
}
