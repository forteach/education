package com.forteach.education.course.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-22 09:49
 * @Version: 1.0
 * @Description:
 */

public interface ICourseChapterDto {

    /**
     * 章节编号
     * @return
     */
    public String getChapterId();

    /**
     * 章节名称
     * @return
     */
    public String getChapterName();

    /**
     * 章节父ID
     * @return
     */
    public String getChapterParentId();

    /**
     * 是否发布
     * @return
     */
    public String getPublish();

    /**
     * 当前层级位置
     * @return
     */
    public Integer getSort();

    /**
     * 当前章节在树状目录处于层级位置
     * @return
     */
    public Integer getChapterLevel();
}
