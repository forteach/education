package com.forteach.education.information.domain;

import com.forteach.education.common.domain.Entitys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 简单公告
 */

@Data
@Entity
@Table(name = "notice")
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "notice", comment = "公告")
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends Entitys implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /** 文章编号.**/
    @Id
    @Column(length = 40, nullable = false, name = "notice_id", columnDefinition = "VARCHAR(40) COMMENT '文章编号'")
    private String noticeId;

    /** 公告内容.**/
    @Column(name = "content", columnDefinition = "VARCHAR(10000) COMMENT '公告内容'")
    private String content;

    /** 公告所属领域.**/
    @Column(name = "area", columnDefinition = "VARCHAR(32) COMMENT '公告所属领域'")
    private String area;

}
