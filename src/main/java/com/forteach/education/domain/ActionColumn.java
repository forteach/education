package com.forteach.education.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 9:30
 */
@Data
@Entity
@Table(name = "action_column")
public class ActionColumn extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "col_id", columnDefinition = "varchar(32) COMMENT '栏目编号'")
    private String colId;

    @Column(name = "col_name", columnDefinition = "varchar(40) COMMENT '栏目名称'")
    private String colName;

    @Column(name = "col_name_model", columnDefinition = "varchar(40) COMMENT '栏目跳转'")
    private String colNameModel;

    @Column(name = "col_parent_id", columnDefinition = "varchar(32) COMMENT '父栏目编号'")
    private String colParentId;

    @Column(name = "col_parent_name", columnDefinition = "varchar(40) COMMENT '父栏目名称'")
    private String colParentName;

    @Column(name = "col_url", columnDefinition = "varchar(255) COMMENT '链接地址'")
    private String colUrl;

    @Column(name = "col_img_url", columnDefinition = "varchar(255) COMMENT '链接图标'")
    private String colImgUrl;

    @Column(name = "is_order", columnDefinition = "int(11) COMMENT '栏目顺序'")
    private String isOrder;

    @Column(name = "is_validated", columnDefinition = "char(1) COMMENT '生效标记'")
    private String isValidated;

    @Column(name = "u_time", columnDefinition = "timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'")
    private Date uTime;

    @Column(name = "c_time", columnDefinition = "datetime COMMENT '创建时间'")
    private Date cTime;

}
