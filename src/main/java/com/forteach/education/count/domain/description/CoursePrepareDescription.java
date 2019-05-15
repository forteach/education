package com.forteach.education.count.domain.description;

import com.forteach.education.count.domain.base.BaseCourseDescription;
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
 * @date: 19-5-15 16:42
 * @version: 1.0
 * @description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "course_prepare_description", indexes = {
        @Index(name = "course_id_index", columnList = "course_id"),
        @Index(name = "chapter_id_index", columnList = "chapter_id"),
        @Index(name = "class_id_index", columnList = "class_id")
})
@org.hibernate.annotations.Table(appliesTo = "course_prepare_description", comment = "预习统计详情表")
public class CoursePrepareDescription extends BaseCourseDescription implements Serializable {

}
