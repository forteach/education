package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:55
 * @Version: 1.0
 * @Description: 音频资料库
 */
@Data
@Entity
@IdClass(AudioDatumFundPrimarykey.class)
@Table(name = "audio_datum", indexes = {@Index(columnList = "audio_id"), @Index(columnList = "chapter_id")})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "audio_datum", comment = "音频资料库")
public class AudioDatum extends Entitys implements Serializable {

    private AudioDatumFundPrimarykey audioDatumFundPrimarykey;

    @Id
    private String audioId;

    private String chapterId;

    @Column(name = "audio_name", columnDefinition = "VARCHAR(255) COMMENT '音频名称'")
    private String audioName;

    @Column(name = "audio_type", columnDefinition = "VARCHAR(10) COMMENT '音频类型'")
    private String audioType;

    @Column(name = "audio_url", columnDefinition = "VARCHAR(255) COMMENT '音频URL'")
    private String audioUrl;
}
