package com.forteach.education.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private String audioId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'")
    @ApiModelProperty(name = "chapterId", value = "章节编号", dataType = "string", required = true)
    private String chapterId;

    @ApiModelProperty(value = "音频名称", name = "audioName", dataType = "string")
    @Column(name = "audio_name", columnDefinition = "VARCHAR(255) COMMENT '音频名称'")
    private String audioName;

    @ApiModelProperty(name = "audioType", value = "a音频类型", dataType = "string")
    @Column(name = "audio_type", columnDefinition = "VARCHAR(10) COMMENT '音频类型'")
    private String audioType;

    @ApiModelProperty(name = "audioType", value = "音频URL", dataType = "string")
    @Column(name = "audio_url", columnDefinition = "VARCHAR(255) COMMENT '音频URL'")
    private String audioUrl;
}
