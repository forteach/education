package com.forteach.education.course.domain;


import com.forteach.education.common.domain.Entitys;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @Description: 科目
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/6 16:42
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@org.hibernate.annotations.Table(appliesTo = "course", comment = "科目")
@Table(name = "course", indexes = {@Index(columnList = "course_id", name = "course_id_index")})
@EqualsAndHashCode(callSuper = true)
public class Course extends Entitys {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '科目编号'")
    private String courseId;

    @Column(name = "course_name", columnDefinition = "VARCHAR(40) COMMENT '科目名称'")
    private String courseName;

    @Column(name = "course_number", columnDefinition = "VARCHAR(32) COMMENT '课程编号'")
    private String courseNumber;

    @Column(name = "share_type", columnDefinition = "INT DEFAULT 1 COMMENT '分享类型：１.私有 2.协作 ３.公开'")
    private String shareType;

    /**
     * TODO 授课类型是多选
     */
    @Column(name = "teaching_type", columnDefinition = "VARCHAR(32) COMMENT '1、录播课程 2、直播课程 3、线下课堂'")
    private String teachingType;

    @Column(name = "lesson_preparation_type", columnDefinition = "INT COMMENT '备课类型　1、单人备课 2、集体备课'")
    private String lessonPreparationType;

    @Column(name = "top_pic_src", columnDefinition = "VARCHAR(255) COMMENT'封面图片路径'")
    private String topPicSrc;

    @Column(name = "course_describe", columnDefinition = "MEDIUMTEXT COMMENT'课程描述'")
    private String courseDescribe;

    @Column(name = "average_score", columnDefinition = "INT DEFAULT 0 COMMENT '课程平均分数'")
    private Integer averageScore;

    @Column(name = "review_amount", columnDefinition = "INT DEFAULT 0 COMMENT '评价数量'")
    private Integer reviewAmount;

}
