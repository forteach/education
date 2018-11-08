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
 * @date: 2018/11/7 17:06
 */
@Data
@Entity
@Table(name = "design")
@EqualsAndHashCode(callSuper = true)
public class Design extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "design_id", columnDefinition = "varchar(32) COMMENT '试题编号'")
    private String designId;

    @Column(name = "design_question", columnDefinition = "varchar(1000) COMMENT '试题内容'")
    private String designQuestion;

    @Column(name = "design_answ", columnDefinition = "varchar(1000) COMMENT '试题答案'")
    private String designAnsw;
}
