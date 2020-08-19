package com.forteach.education.count.domain.base;

import com.forteach.education.common.domain.Entitys;
import com.forteach.education.count.domain.pk.CourseCountPk;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 10:19
 * @version: 1.0
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@IdClass(CourseCountPk.class)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseCourseCount extends Entitys {

    @EmbeddedId
    private CourseCountPk courseCountPk;

    private String courseId;

    private String chapterId;

    private String classId;

    @Column(name = "class_students_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '班级学生人数'")
    private Integer classStudentsNumber;

    @Column(name = "students_number", columnDefinition = "INT(11) DEFAULT 0 COMMENT '参与的学生数量'")
    private Integer studentsNumber;

    @Column(name = "teacher_id", columnDefinition = "VARCHAR(32) COMMENT '教师id'")
    private String teacherId;

}
