package com.forteach.education.databank.dto;

/**
 * @author ：zhang10092009@hotmail.com
 * @date ：Created in 2020/9/25 14:42
 * @description：资料信息统计
 * @modified By：
 * @version: V1.0
 */
public interface ChapterDataNumDto {
    /**
     * 课程id
     * @return
     */
    public String getCourseId();

    /**
     * 课程章节id
     * @return
     */
    public String getChapterId();

    /**
     * 资料信息统计数，音频，视频，资料，图片，链接等
     * @return
     */
    public Long getDataNum();
}
