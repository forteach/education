package com.forteach.education.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 15:44
 * @Version: 1.0
 * @Description: 课程分享范围
 */
@Data
@Embeddable
public class CourseShareFundPrimaryKey implements Serializable {

    @Column(name = "share_id", columnDefinition = "VARCHAR(32) COMMENT '分享编号'", insertable = false, updatable = false)
    private String shareId;

    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'", insertable = false, updatable = false)
    private String courseId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", insertable = false, updatable = false)
    private String chapterId;

    public CourseShareFundPrimaryKey() {
    }

    public CourseShareFundPrimaryKey(String shareId, String courseId, String chapterId) {
        this.shareId = shareId;
        this.courseId = courseId;
        this.chapterId = chapterId;
    }
}
