package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-16 17:26
 * @Version: 1.0
 * @Description: 分享用户
 */
@Entity
@Data
@Table(name = "share_user", indexes = {@Index(columnList = "share_id"), @Index(columnList = "user_id")})
@EqualsAndHashCode(callSuper = true)
@IdClass(ShareUserFundPrimarykey.class)
@org.hibernate.annotations.Table(appliesTo = "share_user", comment = "分享用户")
public class ShareUser extends Entitys implements Serializable {

    @EmbeddedId
    private ShareUserFundPrimarykey shareUserFundPrimarykey;

    @Id
    private String shareId;

    private String userId;

    @Column(name = "user_name", columnDefinition = "VARCHAR(60) COMMENT '成员名称'")
    private String userName;

}
