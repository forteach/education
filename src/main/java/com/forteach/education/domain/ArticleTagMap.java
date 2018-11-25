package com.forteach.education.domain;

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
 * @Description:　文章标签对应表
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 16:33
 */
@Data
@Entity
@Table(name = "article_tag_map", indexes = {@Index(columnList = "article_id"), @Index(columnList = "tagId")})
@EqualsAndHashCode(callSuper = true)
@IdClass(ArticleTagMapFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "article_tag_map", comment = "文章标签对应表")
@ApiModel(value = "文章标签对应表")
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTagMap extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章标签主键", hidden = true)
    @EmbeddedId
    private ArticleTagMapFundPrimarykey articleTagMapFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "articleId", value = "文章编号", dataType = "string")
    private String articleId;

    @ApiModelProperty(name = "tagId", value = "标签编号", dataType = "string")
    private String tagId;


}
