package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:44
 * @Version: 1.0
 * @Description: 视频资料库
 */
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "view_datum", indexes = {@Index(columnList = "view_id"), @Index(columnList = "chapter_id")})
@IdClass(ViewDatumFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "view_datum", comment = "视频资料库")
@ApiModel(value = "视频资料信息")
public class ViewDatum extends Entitys implements Serializable {

    private ViewDatumFundPrimarykey viewDatumFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "viewId", value = "视频编号", notes = "视频资源ID", example = "123456")
    private String viewId;

    @ApiModelProperty(name = "chapterId", value = "章节编号", notes = "章节编号ID", example = "123456")
    private String chapterId;

    @NotNull(message = "视频名称不为空")
    @ApiModelProperty(name = "chapterId", value = "视频名称", notes = "视频名称", example = "学习视频001")
    @Column(name = "view_name", columnDefinition = "VARCHAR(32) COMMENT '视频名称'")
    private String viewName;

    @ApiModelProperty(name = "viewType", value = "视频类型", notes = "", example = "")
    @Column(name = "view_type", columnDefinition = "VARCHAR(10) COMMENT '视频类型'")
    private String viewType;

    @NotNull(message = "视频链接地址不为空")
    @ApiModelProperty(name = "viewUrl", value = "视频URL", notes = "视频的链接地址", example = "http://www.www./dsdd.mp4")
    @Column(name = "view_url", columnDefinition = "VARCHAR(255) COMMENT '视频URL'")
    private String viewUrl;
}
