package com.forteach.education.databank.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 17:30
 * @Version: 1.0
 * @Description: 协作内容访问成员
 */
@Data
@Embeddable
public class ShareUserFundPrimarykey implements Serializable {

    @Column(name = "share_id", columnDefinition = "VARCHAR(32) COMMENT '分享编号'", insertable = false, updatable = false)
    private String shareId;

    @Column(name = "user_id", columnDefinition = "VARCHAR(32) COMMENT '成员编号'", insertable = false, updatable = false)
    private String userId;

    public ShareUserFundPrimarykey() {
    }

    public ShareUserFundPrimarykey(String shareId, String userId) {
        this.shareId = shareId;
        this.userId = userId;
    }
}

