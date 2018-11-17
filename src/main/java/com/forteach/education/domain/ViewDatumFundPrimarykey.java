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
 * @Date: 2018/11/17 13:46
 * @Version: 1.0
 * @Description: 视频资料库
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewDatumFundPrimarykey implements Serializable {

    @Column(name = "view_id", columnDefinition = "VARCHAR(32) COMMENT '视频编号'", insertable = false, updatable = false)
    private String viewId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", insertable = false, updatable = false)
    private String chapterId;
}
