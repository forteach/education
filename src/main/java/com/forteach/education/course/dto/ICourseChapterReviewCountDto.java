package com.forteach.education.course.dto;

/**
 * @author: zhangyy
 * @email: zhang10092009@hotmail.com
 * @date: 19-5-23 12:14
 * @version: 1.0
 * @description:
 */
public interface ICourseChapterReviewCountDto {
    /**
     * 回答人数
     * @return
     */
    public Integer getReviewAmount();

    /**
     * 平均分
     * @return
     */
    public Double getAverageScore();
}
