package com.forteach.education.count.domain;

import com.forteach.education.common.domain.Entitys;
import com.forteach.education.count.domain.pk.CourseCountPk;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:26
 * @version: 1.0
 * @description:
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "course_chapter_count", indexes = {
        @Index(name = "course_id_index", columnList = "course_id"),
        @Index(name = "chapter_id_index", columnList = "chapter_id"),
        @Index(name = "class_id_index", columnList = "class_id"),
        @Index(name = "circle_id_index", columnList = "circle_id")
})
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CourseCountPk.class)
@org.hibernate.annotations.Table(appliesTo = "course_chapter_count", comment = "课程章节统计记录表")
public class CourseChapterCount extends Entitys implements Serializable {

    @EmbeddedId
    private CourseCountPk courseCountPk;

    private String courseId;

    private String chapterId;

    private String classId;

    @Column(name = "circle_id", columnDefinition = "VARCHAR(32) COMMENT '课堂id'")
    private String circleId;

    @Column(name = "class_students_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '班级学生人数'")
    private Integer classStudentsNumber;

    @Column(name = "teacher_id", columnDefinition = "VARCHAR(32) COMMENT '教师id'")
    private String teacherId;

    @Column(name = "join_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '加入课堂的学生数量'")
    private Integer joinNumber;

    @Column(name = "prepare_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '预习的学生数量'")
    private Integer prepareNumber;

    @Column(name = "drill_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '练习的学生数量'")
    private Integer drillNumber;

    @Column(name = "homework_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '课后作业的学生数量'")
    private Integer homeworkNumber;

    @Column(name = "interaction_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '交互互动的学生数量'")
    private Integer interactionNumber;

    @Column(name = "rewards_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '奖励的学生数量'")
    private Integer rewardsNumber;

    @Column(name = "task_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '完成任务学生数量'")
    private Integer taskNumber;
}
