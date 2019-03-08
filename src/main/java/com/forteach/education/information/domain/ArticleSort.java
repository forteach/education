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
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-8 16:02
 * @Version: 1.0
 * @Description: 文章分类
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "article_sort", indexes = {@Index(columnList = "sort_article_id", name = "sort_article_id_index")})
@org.hibernate.annotations.Table(appliesTo = "article_sort", comment = "文章分类")
@ApiModel(value = "文章分类")
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSort extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "sort_article_id", columnDefinition = "VARCHAR(32) COMMENT '分类编号'")
    @ApiModelProperty(name = "sortArticleId", value = "分类编号", dataType = "string")
    private String sortArticleId;

    @ApiModelProperty(name = "sortArticleName", value = "分类名称", dataType = "string", required = true)
    @Column(name = "sort_article_name", columnDefinition = "VARCHAR(32) COMMENT '分类名称'")
    private String sortArticleName;


}
