package com.forteach.education.course.domain;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-4-29 14:14
 * @version: 1.0
 * @description:
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "学生提交评论")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "course_review_describe", comment = "课程评论详情")
@Table(name = "course_review_describe", indexes =
        {@Index(columnList = "review_id", name = "review_id_index"),
        @Index(columnList = "course_id", name = "course_id_index")})
public class CourseReviewDescribe extends Entitys {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "评论编号", name = "review_id", dataType = "string")
    @Column(name = "review_id", columnDefinition = "VARCHAR(32) COMMENT '评论编号'")
    private String reviewId;

    @ApiModelProperty(name = "courseId", value = "课程id", dataType = "string", required = true)
    @Column(name = "course_id", columnDefinition = "VARCHAR(32) COMMENT '课程id'")
    private String courseId;

    @ApiModelProperty(name = "reviewDescribe", value = "评论内容", dataType = "string")
    @Column(name = "review_describe", columnDefinition = "TEXT(5000) COMMENT '评论内容'")
    private String reviewDescribe;

    @ApiModelProperty(hidden = true)
    @Column(name = "student_id", columnDefinition = "VARCHAR(32) COMMENT '学生id'")
    private String studentId;

    @ApiModelProperty(name = "score", value = "评论分数", dataType = "int")
    @Column(name = "score", columnDefinition = "INT(11) COMMENT '评论分数'")
    private Integer score;

    @ApiModelProperty(hidden = true)
    @Column(name = "teacher_id", columnDefinition = "VARCHAR(32) COMMENT '教师id'")
    private String teacherId;

    @ApiModelProperty(name = "reply", value = "老师回复内容", dataType = "string")
    @Column(name = "reply", columnDefinition = "TEXT(5000) COMMENT '老师回复内容'")
    private String reply;

    @ApiModelProperty(hidden = true)
    @Column(name = "reply_time", columnDefinition = "VARCHAR(32) COMMENT '回复时间'")
    private String replyTime = StrUtil.isBlank(this.createTime) ? DateUtil.now() : this.createTime;;
}
