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
 * @Description:　广告标签对应表
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 14:49
 */
@Data
@Entity
@Table(name = "ad_tag_map", indexes = {@Index(columnList = "ad_id", name = "ad_id_index"), @Index(columnList = "tag_id", name = "tag_id_index")})
@EqualsAndHashCode(callSuper = true)
@IdClass(AdTagMapFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "ad_tag_map", comment = "广告标签对应表")
@ApiModel(value = "广告标签对应表")
@AllArgsConstructor
@NoArgsConstructor
public class AdTagMap extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告标签主键", hidden = true)
    @EmbeddedId
    private AdTagMapFundPrimarykey adTagMapFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "adId", value = "广告id", dataType = "string")
    private String adId;

    @ApiModelProperty(name = "tagId", value = "标签编号", dataType = "string", required = true)
    private String tagId;

}
