package com.forteach.education.course.domain.pk;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description: 协作人员访问
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:18
 */
@Data
@Embeddable
public class CourseShareUsersPk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "share_id", columnDefinition = "varchar(32) COMMENT '分享内容ID'", insertable = false, updatable = false)
    private String shareId;

    @Column(name = "user_id", columnDefinition = "varchar(32) COMMENT '成员编号'", insertable = false, updatable = false)
    private String userId;

    public CourseShareUsersPk() {

    }

    public CourseShareUsersPk(String shareId, String userId) {
        this.shareId = shareId;
        this.userId = userId;
    }
}
