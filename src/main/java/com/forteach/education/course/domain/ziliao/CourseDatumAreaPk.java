package com.forteach.education.course.domain.ziliao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Description: 协作人员访问
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/11/7 10:18
 */
@Data
@Embeddable
public class CourseDatumAreaPk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "file_id", columnDefinition = "VARCHAR(32) COMMENT '文件编号'", insertable = false, updatable = false)
    public String fileId;

    @Column(name = "datum_area", columnDefinition = "VARCHAR(32) COMMENT '资料领域 : 1教案 2课件 3预习参考 4课堂参考 5授课案例、6复习参考'", insertable = false, updatable = false)
    private String datumArea;

    public CourseDatumAreaPk() {

    }

    public CourseDatumAreaPk(String fileId, String datumArea) {
        this.fileId = fileId;
        this.datumArea = datumArea;
    }
}
