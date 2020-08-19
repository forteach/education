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
 * @date: 19-5-15 15:44
 * @version: 1.0
 * @description:　课堂奖励详情表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "course_rewards_description", indexes = {
        @Index(name = "course_id_index", columnList = "course_id"),
        @Index(name = "chapter_id_index", columnList = "chapter_id"),
        @Index(name = "class_id_index", columnList = "class_id")
})
@org.hibernate.annotations.Table(appliesTo = "course_rewards_description", comment = "课堂奖级详情记录表")
public class CourseRewardsDescription extends BaseCourseDescription implements Serializable {

//    @Column(name = "grade", columnDefinition = "INT(11) COMMENT '分数'")
//    private Integer grade;

}
