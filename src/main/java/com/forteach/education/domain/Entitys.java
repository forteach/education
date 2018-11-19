package com.forteach.education.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:　表的基本信息
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:53
 */
@Data
@MappedSuperclass
public class Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "is_validated", columnDefinition = "char(1) COMMENT '生效标识 0生效 1失效'")
    private String isValidated;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "u_time", columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date uTime;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "c_time", columnDefinition = "datetime COMMENT '创建时间'")
    private Date cTime;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
