package com.forteach.education.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.forteach.education.filter.View;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 14:08
 * @Version: 1.0
 * @Description: 链接资料库
 */
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "link_datum", comment = "链接资料库")
@Table(name = "link_datum", indexes = {@Index(columnList = "link_id"), @Index(columnList = "chapter_id")})
@ApiModel(value = "链接资料库")
@AllArgsConstructor
@NoArgsConstructor
public class LinkDatum extends Entitys implements Serializable {

    @Id
    @JsonView(View.SummaryExtend.class)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "链接ID", name = "linkId", dataType = "string")
    @Column(name = "link_id", columnDefinition = "VARCHAR(32) COMMENT '链接编号'")
    private String linkId;

    @JsonView(View.Summary.class)
    @NotNull(message = "章节编号不为空")
    @ApiModelProperty(value = "章节编号", name = "chapterId", dataType = "string")
    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    private String chapterId;

    @URL(message = "不是一个URL")
    @JsonView(View.Summary.class)
    @NotNull(message = "链接URL不为空")
    @ApiModelProperty(value = "链接URL", name = "linkUrl", dataType = "string", required = true)
    @Column(name = "link_url", columnDefinition = "VARCHAR(255) COMMENT 'URL'")
    private String linkUrl;

    @JsonView(View.Summary.class)
    @ApiModelProperty(value = "链接名称", name = "linkName", dataType = "string", required = true)
    @Column(name = "link_name", columnDefinition = "VARCHAR(60) COMMENT '链接名称'")
    private String linkName;
}
