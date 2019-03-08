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
 * @Description:　文章详情
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 15:56
 */
@Data
@Entity
@Table(name = "ad_txt", indexes = {@Index(columnList = "ad_id", name = "ad_id_index")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "ad_txt", comment = "文章详情")
@ApiModel(value = "文章详情")
@AllArgsConstructor
@NoArgsConstructor
public class AdTxt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "txt_id", columnDefinition = "varchar(32) COMMENT '广告文本id'")
    @ApiModelProperty(name = "txtId", value = "广告文本id", dataType = "string")
    private String txtId;


    @ApiModelProperty(name = "adId", value = "广告id", dataType = "string")
    @Column(name = "ad_id", columnDefinition = "varchar(32) COMMENT '广告id'")
    private String adId;

    @ApiModelProperty(name = "adText", value = "广告文本内容", dataType = "string")
    @Column(name = "ad_text", columnDefinition = "text(65535) COMMENT '广告文本内容'")
    private String adText;

}
