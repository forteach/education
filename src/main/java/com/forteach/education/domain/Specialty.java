package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: (专业)
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:01
 */
@Data
@Entity
@Table(name = "specialty")
@EqualsAndHashCode(callSuper = true)
public class Specialty extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "specialty_id", columnDefinition = "varchar(32) COMMENT '专业号'")
    private String specialtyId;

    @Id
    @Column(name = "specialty_name", columnDefinition = "varchar(255) COMMENT '专业名称'")
    private String specialtyName;


}