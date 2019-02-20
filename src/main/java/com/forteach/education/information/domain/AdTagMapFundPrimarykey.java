package com.forteach.education.information.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 14:49
 */
@Data
@Embeddable
public class AdTagMapFundPrimarykey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ad_id", columnDefinition = "varchar(32) COMMENT '广告id'", insertable = false, updatable = false)
    private String adId;

    @Column(name = "tag_id", columnDefinition = "varchar(32) COMMENT '标签编号'", insertable = false, updatable = false)
    private String tagId;

    public AdTagMapFundPrimarykey() {
    }

    public AdTagMapFundPrimarykey(String adId, String tagId) {
        this.adId = adId;
        this.tagId = tagId;
    }
}
