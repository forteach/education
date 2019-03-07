package com.forteach.education.authority.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:　系统栏目表
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 9:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "action_column", indexes = {@Index(columnList = "col_id", name = "col_id_index")})
@org.hibernate.annotations.Table(appliesTo = "action_column", comment = "系统栏目表")
@ApiModel(value = "系统栏目表")
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class ActionColumn extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "col_id", columnDefinition = "varchar(32) COMMENT '栏目编号'")
    @ApiModelProperty(name = "colId", value = "栏目编号", dataType = "string")
    private String colId;

    @ApiModelProperty(name = "colName", value = "栏目名称", dataType = "string")
    @Column(name = "col_name", columnDefinition = "varchar(40) COMMENT '栏目名称'")
    private String colName;

    @ApiModelProperty(name = "colNameModel", value = "栏目跳转", dataType = "string")
    @Column(name = "col_name_model", columnDefinition = "varchar(40) COMMENT '栏目跳转'")
    private String colNameModel;

    @ApiModelProperty(name = "colParentId", value = "父栏目编号", dataType = "string")
    @Column(name = "col_parent_id", columnDefinition = "varchar(32) COMMENT '父栏目编号'")
    private String colParentId;

    @ApiModelProperty(name = "colParentName", value = "父栏目名称", dataType = "string")
    @Column(name = "col_parent_name", columnDefinition = "varchar(40) COMMENT '父栏目名称'")
    private String colParentName;

    @ApiModelProperty(name = "colUrl", value = "链接地址", dataType = "string")
    @Column(name = "col_url", columnDefinition = "varchar(255) COMMENT '链接地址'")
    private String colUrl;

    @ApiModelProperty(name = "colImgUrl", value = "链接图标", dataType = "string")
    @Column(name = "col_img_url", columnDefinition = "varchar(255) COMMENT '链接图标'")
    private String colImgUrl;

    @ApiModelProperty(name = "isOrder", value = "栏目顺序", dataType = "string")
    @Column(name = "is_order", columnDefinition = "int(11) COMMENT '栏目顺序'")
    private Integer isOrder;

    @Transient
    private List<ActionColumn> children;

}
