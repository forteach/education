package com.forteach.education.course.domain.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 14:28
 * @version: 1.0
 * @description:
 */
@Data
@Embeddable
public class CourseChapterReviewDescribePk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", updatable = false, insertable = false)
    private String chapterId;

    @Column(name = "student_id", columnDefinition = "VARCHAR(32) COMMENT '学生id'", updatable = false, insertable = false)
    private String studentId;

    public CourseChapterReviewDescribePk() {
    }

    public CourseChapterReviewDescribePk(String chapterId, String studentId) {
        this.chapterId = chapterId;
        this.studentId = studentId;
    }
}
