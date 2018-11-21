package com.forteach.education.domain;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
//@EntityListeners(AuditingEntityListener.class)
public class Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "is_validated", columnDefinition = "CHAR(1) DEFAULT 0 COMMENT '生效标识 0生效 1失效'", nullable=false)
    private String isValidated = "0";

//    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
//    @org.hibernate.annotations.UpdateTimestamp
    @Column(name = "u_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'", nullable=false)
    @Generated(GenerationTime.ALWAYS)
    private Date uTime;

//    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
//    @org.hibernate.annotations.CreationTimestamp
    @Column(updatable = false, name = "c_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'", nullable=false)
    @Generated(GenerationTime.INSERT)
    private Date cTime;

    @Column(name = "c_user", columnDefinition = "VARCHAR(32) COMMENT '创建人'")
    private String cUser;

    @Column(name = "u_user", columnDefinition = "VARCHAR(32) COMMENT '修改人'")
    private String uUser;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
