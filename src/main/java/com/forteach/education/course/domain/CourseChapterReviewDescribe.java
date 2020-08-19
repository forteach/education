package com.forteach.education.course.domain;

import com.forteach.education.common.domain.Entitys;
import com.forteach.education.course.domain.pk.CourseChapterReviewDescribePk;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 10:58
 * @version: 1.0
 * @description:
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@Table(name = "course_chapter_review_describe", indexes = {
        @Index(name = "chapter_id_index", columnList = "chapter_id"),
        @Index(name = "student_id_index", columnList = "student_id")
})
@org.hibernate.annotations.Table(appliesTo = "course_chapter_review_describe", comment = "课程章节评论详情表")
@IdClass(CourseChapterReviewDescribePk.class)
public class CourseChapterReviewDescribe extends Entitys implements Serializable {

    @EmbeddedId
    private CourseChapterReviewDescribePk courseChapterReviewDescribePk;

    private String chapterId;

    private String studentId;

    @Column(name = "score", columnDefinition = "INT(11) COMMENT '评论分数'")
    private Integer score;

}
