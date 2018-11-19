package com.forteach.education.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/16 20:10
 * @Version: 1.0
 * @Description: 章节资料
 */
@Data
@Entity
@IdClass(ChapteDataFundPrimarykey.class)
@Table(name = "chapte_data", indexes = {
        @Index(columnList = "data_id"),
        @Index(columnList = "chapter_id"),
        @Index(columnList = "sort_img_id"),
        @Index(columnList = "file_id"),
        @Index(columnList = "link_id"),
        @Index(columnList = "view_id"),
        @Index(columnList = "audio_id")
})
@EqualsAndHashCode(callSuper = true)
@org.hibernate.annotations.Table(appliesTo = "chapte_data", comment = "章节资料")
public class ChapteData extends Entitys implements Serializable {

    @EmbeddedId
    private ChapteDataFundPrimarykey chapteDataFundPrimarykey;

    @Id
    private String dataId;

    private String courseId;

    private String chapterId;

    private String sortImgId;

    private String fileId;

    private String linkId;

    private String viewId;

    private String audioId;

    @Column(name = "datum_name", columnDefinition = "VARCHAR(60) COMMENT '资料名称'")
    private String datumName;

    @Column(name = "datum_type", columnDefinition = "INT COMMENT '资料类型'")
    private Integer datumType;

    @Column(name = "remark", columnDefinition = "VARCHAR(255) COMMENT '备注说明'")
    private String remark;

}
