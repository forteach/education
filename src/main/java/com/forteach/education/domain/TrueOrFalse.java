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
 * @date: 2018/11/7 17:22
 */
@Data
@Entity
@Table(name = "true_or_false")
@EqualsAndHashCode(callSuper = true)
public class TrueOrFalse extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "question_id", columnDefinition = "varchar(32) COMMENT '题型编号'")
    private String questionId;

    @Column(name = "true_or_false_id", columnDefinition = "varchar(32) COMMENT '试题编号'")
    private String trueOrFalseId;

    @Column(name = "true_or_false__info", columnDefinition = "varchar(500) COMMENT '试题内容'")
    private String trueOrFalseInfo;

    @Column(name = "true_or_false_answ", columnDefinition = "char(1) COMMENT '试题答案'")
    private String trueOrFalseAnsw;

}
