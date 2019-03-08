package com.forteach.education.databank.domain;

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
 * @Description:　简答思考题
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 17:06
 */
@Data
@Entity
@Table(name = "design", indexes = {@Index(columnList = "design_id", name = "design_id_index")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "design", comment = "简答思考题")
@ApiModel(value = "简答思考题")
@AllArgsConstructor
@NoArgsConstructor
public class Design extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "design_id", columnDefinition = "varchar(32) COMMENT '试题编号'")
    @ApiModelProperty(value = "试题编号", name = "design_id", dataType = "string")
    private String designId;

    @ApiModelProperty(value = "试题内容", name = "designQuestion", dataType = "string")
    @Column(name = "design_question", columnDefinition = "varchar(1000) COMMENT '试题内容'")
    private String designQuestion;

    @ApiModelProperty(value = "试题答案", name = "designAnsw", dataType = "string")
    @Column(name = "design_answ", columnDefinition = "varchar(1000) COMMENT '试题答案'")
    private String designAnsw;
}
