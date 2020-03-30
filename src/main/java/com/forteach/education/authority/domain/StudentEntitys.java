package com.forteach.education.authority.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 19-1-7 09:39
 * @Version: 1.0
 * @Description: 从学校查询其它系统导入的学生数据表
 */
@Data
@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@DynamicUpdate
@Table(name = "student_info",indexes = {
        @Index(columnList = "id", name = "id_index"),
        @Index(columnList = "id_card_no", name = "id_card_no_index")
})
@AllArgsConstructor
@NoArgsConstructor
@org.hibernate.annotations.Table(appliesTo = "student_info", comment = "从学校数据库查询的学生信息")
public class StudentEntitys extends Entitys {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(32) COMMENT '学号id'")
    private String id;

    @Column(name = "user_name", columnDefinition = "VARCHAR(32) COMMENT '用户名'")
    private String userName;

    @Column(name = "gender", columnDefinition = "VARCHAR(32) COMMENT '性别'")
    private String gender;

    @Column(name = "id_card_no", columnDefinition = "VARCHAR(32) COMMENT '身份证号码'")
    private String idCardNo;

    @Column(name = "portrait", columnDefinition = "VARCHAR(255) COMMENT '学生头像url'")
    private String portrait;

    @Column(name = "class_id", columnDefinition = "VARCHAR(32) COMMENT '学生所属班级id'")
    private String classId;

    @Column(name = "class_name", columnDefinition = "varchar(32) comment '班级名称'")
    private String className;

    @Column(name = "grade", columnDefinition = "VARCHAR(32) COMMENT '年级'")
    private String grade;

    @Column(name = "phone", columnDefinition = "VARCHAR(32) COMMENT '电话号码'")
    private String phone;

    @Column(name = "birth_date", columnDefinition = "VARCHAR(32) COMMENT '出生日期'")
    private String birthDate;

    @Column(name = "is_graduate", columnDefinition = "VARCHAR(32) COMMENT '是否毕业'")
    private String isGraduate;

    @Column(name = "student_status", columnDefinition = "VARCHAR(32) COMMENT '学籍状态'")
    private String studentStatus;
}
