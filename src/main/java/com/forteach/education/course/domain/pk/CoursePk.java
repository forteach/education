package com.forteach.education.course.domain.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-29 11:12
 * @version: 1.0
 * @description:
 */
@Data
@Embeddable
public class CoursePk implements Serializable {

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'", insertable = false, updatable = false)
    private String courseId;

    @Column(name = "alias", columnDefinition = "VARCHAR(32) COMMENT '别名'", insertable = false, updatable = false)
    private String alias;
}
