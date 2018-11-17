package com.forteach.education.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/16 20:12
 * @Version: 1.0
 * @Description: 章节资料
 */
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class ChapteDataFundPrimarykey implements Serializable {

    @Column(name = "data_id", columnDefinition = "VARCHAR(32) COMMENT '资料编号'", insertable = false, updatable = false)
    private String dataId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", insertable = false, updatable = false)
    private String chapterId;

    @Column(name = "sort_img_id", columnDefinition = "VARCHAR(32) COMMENT '图册编号'", insertable = false, updatable = false)
    private String sortImgId;

    @Column(name = "file_id", columnDefinition = "VARCHAR(32) COMMENT '文件编号'", insertable = false, updatable = false)
    private String fileId;

    @Column(name = "link_id", columnDefinition = "VARCHAR(32) COMMENT '链接编号'", insertable = false, updatable = false)
    private String linkId;

    @Column(name = "view_id", columnDefinition = "VARCHAR(32) COMMENT '视频编号'", insertable = false, updatable = false)
    private String viewId;

    @Column(name = "audio_id", columnDefinition = "VARCHAR(32) COMMENT '视频编号2'", insertable = false, updatable = false)
    private String audioId;

}
