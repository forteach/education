package com.forteach.education.common.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
@EntityListeners(AuditingEntityListener.class)
public class Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView(View.SummaryExtend.class)
    @Column(name = "is_validated", columnDefinition = "CHAR(1) DEFAULT 0 COMMENT '生效标识 0生效 1失效'", nullable = false)
    @ApiModelProperty(value = "生效标示", name = "isValidated", notes = "生效标识 0生效 1失效 默认生效 0 ", dataType = "string", example = "0")
    private String isValidated = "0";

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonView(View.SummaryDetail.class)
//    @org.hibernate.annotations.UpdateTimestamp
    @Column(name = "u_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'", nullable=false)
//    @Generated(GenerationTime.ALWAYS)
    @ApiModelProperty(value = "修改时间", name = "uTime", dataType = "date", notes = "修改的时间", hidden = true, example = "2018-12-05 04:56:45")
    private Date uTime;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
//    @org.hibernate.annotations.CreationTimestamp
    @JsonView(View.SummaryDetail.class)
    @Column(updatable = false, name = "c_time", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'", nullable = false)
//    @Generated(GenerationTime.INSERT)
    @ApiModelProperty(value = "创建时间", name = "cTime", dataType = "date", notes = "创建时间", hidden = true, example = "2018-12-05 04:56:45")
    private Date cTime;

    @CreatedBy
    @JsonView(View.SummaryDetail.class)
    @Column(updatable = false, name = "c_user", columnDefinition = "VARCHAR(32) COMMENT '创建人'")
    @ApiModelProperty(value = "创建人", name = "cUser", dataType = "string", notes = "创建人ID", hidden = true, example = "df4sf4dsf4s34")
    private String cUser;

    @LastModifiedBy
    @JsonView(View.SummaryDetail.class)
    @Column(name = "u_user", columnDefinition = "VARCHAR(32) COMMENT '修改人'"//, nullable = true
             )
    @ApiModelProperty(value = "修改人", name = "uUser", dataType = "string", notes = "修改人ID", hidden = true, example = "df4sf4dsf4s34")
    private String uUser;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}