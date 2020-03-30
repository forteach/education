package com.forteach.education.course.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forteach.education.common.domain.Entitys;
import com.forteach.education.course.domain.pk.CourseChapterReviewPk;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 10:43
 * @version: 1.0
 * @description:
 */
@Data
@DynamicInsert
@DynamicUpdate
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@IdClass(CourseChapterReviewPk.class)
@Table(name = "course_chapter_review", indexes = {
        @Index(columnList = "chapter_id", name = "chapter_id_index")
})
@org.hibernate.annotations.Table(appliesTo = "course_chapter_review", comment = "章节评论")
public class CourseChapterReview extends Entitys implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private CourseChapterReviewPk courseChapterReviewPk;

    private String chapterId;

    @Column(name = "average_score", columnDefinition = "VARCHAR(32) DEFAULT 0 COMMENT '评价平均分数'")
    private String averageScore;

    @Column(name = "review_amount", columnDefinition = "INT(11) DEFAULT 0 COMMENT '评价数量'")
    private Integer reviewAmount;

}
