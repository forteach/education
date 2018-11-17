package com.forteach.education.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/17 13:58
 * @Version: 1.0
 * @Description: 音频资料库
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AudioDatumFundPrimarykey implements Serializable {

    @Column(name = "audio_id", columnDefinition = "VARCHAR(32) COMMENT '视频编号'", insertable = false, updatable = false)
    private String audioId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", insertable = false, updatable = false)
    private String chapterId;
}
