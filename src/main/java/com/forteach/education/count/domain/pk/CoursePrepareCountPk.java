package com.forteach.education.count.domain.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 10:37
 * @version: 1.0
 * @description:
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CoursePrepareCountPk implements Serializable {

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程id'", insertable = false, updatable = false)
    private String courseId;

    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '章节id'", insertable = false, updatable = false)
    private String chapterId;

    @Column(name = "class_id", columnDefinition = "VARCHAR(32) COMMENT '班级id'", insertable = false, updatable = false)
    private String classId;

}
