package com.forteach.education.information.domain;

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　广告标签
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 11:06
 */
@Data
@Entity
@Table(name = "ad_tag", indexes = {@Index(name = "tag_id_index", columnList = "tag_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "ad_tag", comment = "广告标签")
@ApiModel(value = "广告标签")
@AllArgsConstructor
@NoArgsConstructor
public class AdTag extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "tag_id", columnDefinition = "varchar(32) COMMENT '标签编号'")
    @ApiModelProperty(name = "tagId", value = "标签编号", dataType = "string")
    private String tagId;

    @ApiModelProperty(name = "tagName", value = "标签名称", dataType = "string", required = true)
    @Column(name = "tag_name", columnDefinition = "varchar(60) COMMENT '标签名称'")
    private String tagName;
}
