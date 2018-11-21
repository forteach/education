package com.forteach.education.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@IdClass(AudioDatumFundPrimarykey.class)
@Table(name = "audio_datum", indexes = {@Index(columnList = "audio_id"), @Index(columnList = "chapter_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "audio_datum", comment = "音频资料库")
public class AudioDatum extends Entitys implements Serializable {

    private AudioDatumFundPrimarykey audioDatumFundPrimarykey;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    @ApiModelProperty(value = "audioId", name = "音频ID")
    private String audioId;

    @ApiModelProperty(value = "chapterId", name = "章节编号")
    private String chapterId;

    @ApiModelProperty(name = "音频名称", value = "audioName")
    @Column(name = "audio_name", columnDefinition = "VARCHAR(255) COMMENT '音频名称'")
    private String audioName;

    @ApiModelProperty(value = "audioType", name = "a音频类型")
    @Column(name = "audio_type", columnDefinition = "VARCHAR(10) COMMENT '音频类型'")
    private String audioType;

    @ApiModelProperty(value = "audioType", name = "音频URL")
    @Column(name = "audio_url", columnDefinition = "VARCHAR(255) COMMENT '音频URL'")
    private String audioUrl;
}
