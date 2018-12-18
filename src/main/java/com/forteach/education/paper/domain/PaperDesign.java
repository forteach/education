package com.forteach.education.paper.domain;

/**
 * @Description: 试卷简答思考题
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 11:52
 */

import com.forteach.education.common.domain.Entitys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "paper_design", indexes = {@Index(columnList = "paper_id"), @Index(columnList = "design_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(PaperDesignFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "paper_design", comment = "试卷简答思考题")
@ApiModel(value = "试卷简答思考题")
@AllArgsConstructor
@NoArgsConstructor
public class PaperDesign extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @ApiModelProperty(value = "试卷简答思考题主键", hidden = true)
    private PaperDesignFundPrimarykey paperDesignFundPrimarykey;

    @ApiModelProperty(value = "试卷编号", name = "paperId", dataType = "string", required = true)
    private String paperId;

    @ApiModelProperty(value = "试题编号", name = "designId", dataType = "string", required = true)
    private String designId;


}
