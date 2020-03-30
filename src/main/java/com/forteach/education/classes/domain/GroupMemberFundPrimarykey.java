package com.forteach.education.classes.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 14:42
 * @Version: 1.0
 * @Description: 小组成员
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class GroupMemberFundPrimarykey implements Serializable {

    @Column(name = "group_id", columnDefinition = "VARCHAR(32) COMMENT '小组编号'", insertable = false, updatable = false)
    private String groupId;

    @Column(name = "member_id", columnDefinition = "VARCHAR(32) COMMENT '组员编号'", insertable = false, updatable = false)
    private String memberId;
}
