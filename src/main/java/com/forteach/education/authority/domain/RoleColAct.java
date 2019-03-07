package com.forteach.education.authority.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　权限和动作
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 8:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role_col_act", indexes = {@Index(columnList = "sys_act_id", name = "sys_act_id_index"),
        @Index(columnList = "role_id", name = "role_id_index")})
@IdClass(RoleColActFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "role_col_act", comment = "权限和动作")
public class RoleColAct extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RoleColActFundPrimarykey roleColActFundPrimarykey;

    private String colId;

    private String sysActId;

    private String roleId;

}

