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
 * @Date: 2018/11/17 14:10
 * @Version: 1.0
 * @Description:
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDatumFundPrimarykey implements Serializable {

    @Column(name = "link_id", columnDefinition = "VARCHAR(32) COMMENT '链接编号'", insertable = false, updatable = false)
    private String linkId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", insertable = false, updatable = false)
    private String chapterId;
}
