package com.forteach.education.count.domain;

import com.forteach.education.count.domain.base.BaseCourseCount;
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
 * @date: 19-5-10 10:18
 * @version: 1.0
 * @description: 预习
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "course_prepare_count", indexes = {
        @Index(name = "course_id_index", columnList = "course_id"),
        @Index(name = "chapter_id_index", columnList = "chapter_id"),
        @Index(name = "class_id_index", columnList = "class_id")
})
@org.hibernate.annotations.Table(appliesTo = "course_prepare_count", comment = "预习统计记录表")
public class CoursePrepareCount extends BaseCourseCount implements Serializable {


    @Column(name = "students", columnDefinition = "TEXT(10000) COMMENT '参与的人(预习,加入,回答)'")
    private String students;
}
