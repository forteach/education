package com.forteach.education.count.domain;

import com.forteach.education.count.domain.base.BaseCourseCount;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-10 14:41
 * @version: 1.0
 * @description:　任务
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "course_task_count", indexes = {
        @Index(name = "course_id_index", columnList = "course_id"),
        @Index(name = "chapter_id_index", columnList = "chapter_id"),
        @Index(name = "class_id_index", columnList = "class_id")
})
@org.hibernate.annotations.Table(appliesTo = "course_task_count", comment = "任务统计记录表")
public class CourseTaskCount extends BaseCourseCount implements Serializable {

}
