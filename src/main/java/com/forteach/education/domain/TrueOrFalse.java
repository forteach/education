package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　判断题
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 17:22
 */
@Data
@Entity
@Table(name = "true_or_false", indexes = {@Index(columnList = "question_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "true_or_false", comment = "判断题")
public class TrueOrFalse extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "question_id", columnDefinition = "varchar(32) COMMENT '题型编号'")
    private String questionId;

    @Column(name = "true_or_false_id", columnDefinition = "varchar(32) COMMENT '试题编号'")
    private String trueOrFalseId;

    @Column(name = "true_or_false__info", columnDefinition = "varchar(500) COMMENT '试题内容'")
    private String trueOrFalseInfo;

    @Column(name = "true_or_false_answ", columnDefinition = "char(1) COMMENT '试题答案'")
    private String trueOrFalseAnsw;

}
