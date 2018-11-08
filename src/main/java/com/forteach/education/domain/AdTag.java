package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 11:06
 */
@Data
@Entity
@Table(name = "ad_tag")
@EqualsAndHashCode(callSuper = true)
public class AdTag extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "tag_id", columnDefinition = "varchar(32) COMMENT '标签编号'")
    private String tagId;

    @Column(name = "tag_name", columnDefinition = "varchar(60) COMMENT '标签名称'")
    private String tagName;
}
