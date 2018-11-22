package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description:　选择题选项
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 9:29
 */
@Data
@Entity
@Table(name = "choice_qst_opt", indexes = {@Index(columnList = "choice_qst_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "choice_qst_opt", comment = "选择题选项")
@ApiModel(value = "选择题选项")
public class ChoiceQstOpt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "choice_qst_id", columnDefinition = "varchar(32) COMMENT '试题编号'")
    @ApiModelProperty(value = "试题编号", name = "choiceQstId", dataType = "string")
    private String choiceQstId;

    @ApiModelProperty(value = "选项ID", name = "optId", dataType = "string", required = true)
    @Column(name = "opt_id", columnDefinition = "varchar(32) COMMENT '选项ID'")
    private String optId;

    @ApiModelProperty(value = "选项描述", name = "optTxt", dataType = "string")
    @Column(name = "opt_txt", columnDefinition = "varchar(255) COMMENT '选项描述'")
    private String optTxt;

    @ApiModelProperty(value = "选项值", name = "optValue", dataType = "string", required = true)
    @Column(name = "opt_value", columnDefinition = "varchar(2) COMMENT '选项值'")
    private String optValue;


}
