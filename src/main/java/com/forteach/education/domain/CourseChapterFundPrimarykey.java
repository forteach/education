package com.forteach.education.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 14:36
 * @Version: 1.0
 * @Description: 科目章节
 */
@Data
@Embeddable
public class CourseChapterFundPrimarykey implements Serializable {

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'", insertable = false,updatable = false)
    private String courseId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", insertable = false,updatable = false)
    private String chapterId;

    public CourseChapterFundPrimarykey() {
    }

    public CourseChapterFundPrimarykey(String courseId, String chapterId) {
        this.courseId = courseId;
        this.chapterId = chapterId;
    }
}
