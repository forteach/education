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
 * @Description: 文章详情
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 16:16
 */
@Data
@Entity
@Table(name = "article_txt", indexes = {@Index(columnList = "article_id", name = "article_id_index")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "article_txt", comment = "文章详情")
@ApiModel(value = "文章详情")
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTxt extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "txt_id", columnDefinition = "varchar(32) COMMENT '文章详情ID'")
    @ApiModelProperty(name = "txtId", value = "文章详情ID", dataType = "string")
    private String txtId;

    @ApiModelProperty(name = "articleId", value = "文章编号", dataType = "string")
    @Column(name = "article_id", columnDefinition = "varchar(32) COMMENT '文章编号'")
    private String articleId;

    @ApiModelProperty(name = "articleConten", value = "文章内容", dataType = "string")
    @Column(name = "article_conten", columnDefinition = "text(65535) COMMENT '文章内容'")
    private String articleConten;

}
