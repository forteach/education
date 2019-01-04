package com.forteach.education.common.domain;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


/**
 * @Description:　表的基本信息
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:53
 */

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Entitys {

//    @JsonView(View.SummaryExtend.class)
    @Column(name = "is_validated", columnDefinition = "CHAR(1) DEFAULT 0 COMMENT '生效标识 0生效 1失效'", nullable = false)
//    @ApiModelProperty(value = "生效标示", name = "isValidated", notes = "生效标识 0生效 1失效 默认生效 0 ", dataType = "string", example = "0")
    public String isValidated = "0";

//    @LastModifiedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonView(View.SummaryDetail.class)
//    @org.hibernate.annotations.UpdateTimestamp
    @Column(name = "u_time", columnDefinition = "VARCHAR(32)  COMMENT '更新时间'")
//    @Generated(GenerationTime.ALWAYS)
//    @ApiModelProperty(value = "修改时间", name = "uTime", dataType = "date", notes = "修改的时间", hidden = true, example = "2018-12-05 04:56:45")
    public String uTime=DateUtil.now();

//    @CreatedDate
//    @Temporal(TemporalType.TIMESTAMP)
//    @org.hibernate.annotations.CreationTimestamp
//    @JsonView(View.SummaryDetail.class)
    @Column(updatable = false, name = "c_time", columnDefinition = "VARCHAR(32) COMMENT '创建时间'")
//    @Generated(GenerationTime.INSERT)
//    @ApiModelProperty(value = "创建时间", name = "cTime", dataType = "date", notes = "创建时间", hidden = true, example = "2018-12-05 04:56:45")
    public String cTime=StrUtil.isBlank(this.cTime)?DateUtil.now():this.cTime;

//    @CreatedBy
//    @JsonView(View.SummaryDetail.class)
    @Column(updatable = false, name = "c_user", columnDefinition = "VARCHAR(32) COMMENT '创建人'")
//    @ApiModelProperty(value = "创建人", name = "cUser", dataType = "string", notes = "创建人ID", hidden = true, example = "df4sf4dsf4s34")
    public String cUser;

//    @LastModifiedBy
//    @JsonView(View.SummaryDetail.class)
    @Column(name = "u_user", columnDefinition = "VARCHAR(32) COMMENT '修改人'")
//    @ApiModelProperty(value = "修改人", name = "uUser", dataType = "string", notes = "修改人ID", hidden = true, example = "df4sf4dsf4s34")
    public String uUser;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    //
//    public String getIsValidated() {
//        return isValidated;
//    }
//
//    public String getuTime() {
//        return uTime;
//    }
//
//    public String getcTime() {
//        return cTime;
//    }
//
//    public String getcUser() {
//        return cUser;
//    }
//
//    public String getuUser() {
//        return uUser;
//    }
//
//    public void setIsValidated(String isValidated) {
//        this.isValidated = isValidated;
//    }
//
//    public void setuTime(String uTime) {
//        if(uTime==null){
//            this.uTime = DateUtil.now();
//        }else {
//            this.uTime=uTime;
//        }
//
//    }
//
//    public void setcTime(String cTime) {
//        if(cTime==null){
//            this.cTime = DateUtil.now();
//        }else {
//            this.cTime=cTime;
//        }
//    }
//
//    public void setcUser(String cUser) {
//        this.cUser = cUser;
//    }
//
//    public void setuUser(String uUser) {
//        this.uUser = uUser;
//    }
}
