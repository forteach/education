package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: 系统公告通知  通知方式：1通知所有人，0不采用
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/8 16:08
 */
@Data
@Entity
@Table(name = "system_message")
@EqualsAndHashCode(callSuper = true)
public class SystemMessage extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "system_id", columnDefinition = "varchar(32) COMMENT '系统通知id'")
    private String systemId;

    @Column(name = "system_topic", columnDefinition = "varchar(60) COMMENT '通知标题'")
    private String systemTopic;

    @Column(name = "system_content", columnDefinition = "varchar(255) COMMENT '通知内容'")
    private String systemContent;

    @Column(name = "send_default", columnDefinition = "bit(1) COMMENT '通知方式'")
    private Boolean sendDefault;

}
