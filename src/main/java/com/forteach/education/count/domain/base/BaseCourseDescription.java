package com.forteach.education.count.domain.base;

import com.forteach.education.common.domain.Entitys;
import com.forteach.education.count.domain.pk.CourseCountPk;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EmbeddedId;
import javax.persistence.EntityListeners;
import javax.persistence.IdClass;
import javax.persistence.MappedSuperclass;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 15:52
 * @version: 1.0
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@IdClass(CourseCountPk.class)
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseCourseDescription extends Entitys {

    @EmbeddedId
    private CourseCountPk courseCountPk;

    private String courseId;

    private String chapterId;

    private String classId;

}
