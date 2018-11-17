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
 * @Date: 2018/11/17 13:30
 * @Version: 1.0
 * @Description: 文档资料库
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDatumFundPrimarykey implements Serializable {

    @Column(name = "file_id", columnDefinition = "VARCHAR(32) COMMENT '文件编号'", insertable = false, updatable = false)
    private String fileId;

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", insertable = false, updatable = false)
    private String chapterId;

}
