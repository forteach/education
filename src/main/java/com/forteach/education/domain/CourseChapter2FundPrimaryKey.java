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
 * @Date: 2018/11/16 20:02
 * @Version: 1.0
 * @Description: 科目章节 2
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CourseChapter2FundPrimaryKey implements Serializable {

    @Column(name = "chapter_id", columnDefinition = "CHAR(32) COMMENT '章节编号'", insertable = false, updatable = false)
    private String chapterId;

    @Column(name = "choice_qst_id", columnDefinition = "VARCHAR(32) COMMENT '试题编号'", insertable = false, updatable = false)
    private String choiceQstId;
}
