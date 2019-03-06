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
import java.util.Date;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-8 16:22
 * @Version: 1.0
 * @Description: 文章
 */
@Data
@Entity
@Table(name = "article", indexes = {@Index(columnList = "article_id", name = "article_id_index"), @Index(columnList = "sort_article_id", name = "sort_article_id_index")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "article", comment = "文章")
@ApiModel(value = "文章")
@AllArgsConstructor
@NoArgsConstructor
public class Article extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "article_id", columnDefinition = "varchar(32) COMMENT '文章编号'")
    @ApiModelProperty(name = "articleId", value = "文章编号", dataType = "string")
    private String articleId;

    @ApiModelProperty(name = "sortArticleId", value = "分类编号", dataType = "string", required = true)
    @Column(name = "sort_article_id", columnDefinition = "VARCHAR(32) COMMENT '分类编号'")
    private String sortArticleId;

    @ApiModelProperty(name = "articleName", value = "文章名称", dataType = "string", required = true)
    @Column(name = "article_name", columnDefinition = "varchar(128) COMMENT'文章名称'")
    private String articleName;

    @ApiModelProperty(name = "articleTime", value = "发布时间", dataType = "date")
    @Column(name = "article_time", columnDefinition = "datetime COMMENT '发布时间'")
    private Date articleTime;

    @ApiModelProperty(name = "articleType", value = "文章模式", dataType = "boolean")
    @Column(name = "article_type", columnDefinition = "bit(1) COMMENT '文章模式'")
    private Boolean articleType;

    @ApiModelProperty(name = "titleImage", value = "标题图片", dataType = "string")
    @Column(name = "title_image", columnDefinition = "VARCHAR(255) COMMENT '标题图片'")
    private String titleImage;


}
