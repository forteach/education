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
 * @Date: 2018/11/17 13:55
 * @Version: 1.0
 * @Description: 音频资料库
 */
@Builder
@Data
@Entity
@Table(name = "audio_datum", indexes = {@Index(columnList = "audio_id"), @Index(columnList = "chapter_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "audio_datum", comment = "音频资料库")
@ApiModel(value = "音频资料库")
@NoArgsConstructor
@AllArgsConstructor
public class AudioDatum extends Entitys implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(name = "audioId", value = "音频ID", dataType = "string")
    @Column(name = "audio_id", columnDefinition = "VARCHAR(32) COMMENT '视频编号'")
    @JsonView(View.SummaryExtend.class)
    private String audioId;

    @NotNull(message = "章节编号不为空")
    @JsonView(View.SummaryExtend.class)
    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    @ApiModelProperty(name = "chapterId", value = "章节编号", dataType = "string", required = true)
    private String chapterId;

    @JsonView(View.Summary.class)
    @NotNull(message = "音频名称不为空")
    @ApiModelProperty(value = "音频名称", name = "audioName", dataType = "string", required = true)
    @Column(name = "audio_name", columnDefinition = "VARCHAR(255) COMMENT '音频名称'")
    private String audioName;

    @JsonView(View.SummaryExtend.class)
    @ApiModelProperty(name = "audioType", value = "音频类型", dataType = "string")
    @Column(name = "audio_type", columnDefinition = "VARCHAR(10) COMMENT '音频类型'")
    private String audioType;

    @URL(message = "不是一个URL")
    @JsonView(View.Summary.class)
    @NotNull(message = "音频URL不为空")
    @ApiModelProperty(name = "audioType", value = "音频URL", dataType = "string", required = true)
    @Column(name = "audio_url", columnDefinition = "VARCHAR(255) COMMENT '音频URL'")
    private String audioUrl;
}
