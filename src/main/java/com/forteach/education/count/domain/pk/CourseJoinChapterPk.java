package com.forteach.education.count.domain.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-6 15:13
 * @version: 1.0
 * @description:　学生加入课堂的主键
 */
@Data
@Embeddable
public class CourseJoinChapterPk implements Serializable {

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程id'", insertable = false, updatable = false)
    private String courseId;

    @Column(name = "chapter_id", columnDefinition = "VARCHAR(32) COMMENT '章节id'", insertable = false, updatable = false)
    private String chapterId;

    @Column(name = "circle_id", columnDefinition = "VARCHAR(32) COMMENT '课堂id'", insertable = false, updatable = false)
    private String circleId;

    @Column(name = "class_id", columnDefinition = "VARCHAR(32) COMMENT '班级id'", insertable = false, updatable = false)
    private String classId;

}
