package com.forteach.education.course.domain.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 10:49
 * @version: 1.0
 * @description:
 */
@Data
@Embeddable
public class CourseChapterReviewPk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", updatable = false, insertable = false)
    private String chapterId;

    public CourseChapterReviewPk() {
    }

    public CourseChapterReviewPk(String chapterId) {
        this.chapterId = chapterId;
    }
}
