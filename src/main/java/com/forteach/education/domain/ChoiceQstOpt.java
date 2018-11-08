package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 9:29
 */
@Data
@Entity
@Table(name = "choice_qst_opt", indexes = {@Index(columnList = "choice_qst_id")})
@EqualsAndHashCode(callSuper = true)
public class ChoiceQstOpt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "choice_qst_id", columnDefinition = "varchar(32) COMMENT '试题编号'")
    private String choiceQstId;

    @Column(name = "opt_id", columnDefinition = "varchar(32) COMMENT '选项ID'")
    private String optId;

    @Column(name = "opt_txt", columnDefinition = "varchar(255) COMMENT '选项描述'")
    private String optTxt;

    @Column(name = "opt_value", columnDefinition = "varchar(2) COMMENT '选项值'")
    private String optValue;


}
