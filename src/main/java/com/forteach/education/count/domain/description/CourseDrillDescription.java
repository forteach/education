package com.forteach.education.count.domain.description;

import com.forteach.education.count.domain.base.BaseCourseDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-15 16:11
 * @version: 1.0
 * @description: 课堂练习记录详情表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "course_drill_description", indexes = {
        @Index(name = "course_id_index", columnList = "course_id"),
        @Index(name = "chapter_id_index", columnList = "chapter_id"),
        @Index(name = "class_id_index", columnList = "class_id")
})
@org.hibernate.annotations.Table(appliesTo = "course_drill_description", comment = "课堂练习详情记录表")
public class CourseDrillDescription extends BaseCourseDescription implements Serializable {

    @Column(name = "true_or_false", columnDefinition = "TINYINT(1) COMMENT '回答对错 1 true 0 flase'")
    private Boolean trueOrFalse;

}
