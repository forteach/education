package com.forteach.education.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 15:00
 * @Version: 1.0
 * @Description: 班级分组
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ClassGroupFundPrimarykey implements Serializable {

    @Column(name = "group_id", columnDefinition = "VARCHAR(32) COMMENT '小组编号'", insertable = false, updatable = false)
    private String groupId;

    @Column(name = "class_id", columnDefinition = "VARCHAR(32) COMMENT '班级编号'", insertable = false, updatable = false)
    private String classId;
}
