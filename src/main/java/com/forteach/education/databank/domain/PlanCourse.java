package com.forteach.education.databank.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/9/8 16:26
 * @Version: v1.0
 * @Modified：排课信息
 * @Description:
 */
@Data
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@Table(name = "plan_course", indexes = {
        @Index(columnList = "id", name = "id_index"),
        @Index(columnList = "teacher_id", name = "teacher_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")
})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "plan_course", comment = "排课信息,从数字化校园查询的")
public class PlanCourse extends Entitys implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "varchar(32) comment '主键id'")
    private String id;

    @Column(name = "course_id", columnDefinition = "varchar(32) comment '课程id'")
    private String courseId;

    @Column(name = "year", columnDefinition = "varchar(32) comment '学年'")
    private String year;

    @Column(name = "semester", columnDefinition = "varchar(32) comment '学期'")
    private String semester;

    @Column(name = "course_name", columnDefinition = "varchar(32) comment '课程名称'")
    private String courseName;

    @Column(name = "teacher_id", columnDefinition = "varchar(32) comment '教师id'")
    private String teacherId;

    @Column(name = "teacher_name", columnDefinition = "varchar(32) comment '教师姓名'")
    private String teacherName;

    @Column(name = "class_room_num", columnDefinition = "varchar(32) comment '节次'")
    private String classRoomNum;

    @Column(name = "course_type", columnDefinition = "varchar(32) comment '课程类别'")
    private String courseType;
    /**
     * 周别 0 不分单双周
     * 1 单周 2 双周
     */
    @Column(name = "week_type", columnDefinition = "varchar(32) comment '周别'")
    private String weekType;

    @Column(name = "class_id", columnDefinition = "varchar(32) comment '班级id'")
    private String classId;

    @Column(name = "class_room_id", columnDefinition = "varchar(32) comment '上课的班级教室id'")
    private String classRoomId;

    @Column(name = "class_room_name", columnDefinition = "varchar(32) comment '上课的教室名称'")
    private String classRoomName;

    @Column(name = "week_num", columnDefinition = "varchar(32) comment '上课的星期(数字)'")
    private String weekNum;


    @Column(name = "plan_course_desc", columnDefinition = "varchar(32) comment '排课描述'")
    private String planCourseDesc;

    /**
     * 周次 1-15
     */
    @Column(name = "class_room_week_num", columnDefinition = "varchar(32) comment '周次(1-15)'")
    private String classRoomWeekNum;
}