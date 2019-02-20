package com.forteach.education.common.request;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @Description:　表的基本信息
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/30 15:53
 */

@Data
public abstract class BaseReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "生效标示", name = "isValidated", notes = "生效标识 0生效 1失效 默认生效 0 ", dataType = "string", example = "0")
    private String isValidated = "0";


    @ApiModelProperty(value = "修改时间", name = "uTime", dataType = "date", notes = "修改的时间", hidden = true, example = "2018-12-05 04:56:45")
    private String uTime;

    @ApiModelProperty(value = "创建时间", name = "cTime", dataType = "date", notes = "创建时间", hidden = true, example = "2018-12-05 04:56:45")
    private String cTime;


    @ApiModelProperty(value = "创建人", name = "cUser", dataType = "string", notes = "创建人ID", hidden = true, example = "df4sf4dsf4s34")
    private String cUser;


    @ApiModelProperty(value = "修改人", name = "uUser", dataType = "string", notes = "修改人ID", hidden = true, example = "df4sf4dsf4s34")
    private String uUser;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

//
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
